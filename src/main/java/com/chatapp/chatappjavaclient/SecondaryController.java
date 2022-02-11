package com.chatapp.chatappjavaclient;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class SecondaryController implements IMessageHandler {

    @FXML
    public Label helloMessageLabel;
    
    @FXML
    public TextArea chatContent;
    
    public String chatContentAsString = "";
    
    @FXML
    public TextField messageField;
    
    public Thread clientReceiverThread;
    
    @FXML
    public void initialize() {
        System.out.println("Segunda tela");
        helloMessageLabel.setText("Bem-vindo(a), " + App.username);
        chatContent.setText(this.chatContentAsString);
        this.createClientReceiver();
    }
    
    public String getAllMessages() {
        return this.chatContentAsString;
    }
   
    public void handleNewMessage(String newMessage) {
        this.chatContentAsString += "\n" + newMessage;
    }
    
    private void createClientReceiver() {
        ClientReceiverTask task = new ClientReceiverTask(this);
        this.clientReceiverThread = new Thread(task);
        this.clientReceiverThread.start();
        this.chatContent.textProperty().bind(task.messageProperty());
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        JSONObject messageToServer = new JSONObject();
        messageToServer.put("username", App.username);
        messageToServer.put("message", "desconectar");
        String finalMessage = messageToServer.toString() + '\n';
        App.serverConnection.outToServer.writeBytes(finalMessage);
        // Melhorar
        clientReceiverThread.interrupt();
        App.setRoot("primary");
    }
    
    @FXML
    private void sendMessageToServer() throws IOException {
        String message = messageField.textProperty().get();
        System.out.println(message);
        JSONObject messageToServer = new JSONObject();
        messageToServer.put("username", App.username);
        messageToServer.put("message", message);
        String finalMessage = messageToServer.toString() + '\n';
        App.serverConnection.outToServer.writeBytes(finalMessage);
        messageField.setText("");
    }
}