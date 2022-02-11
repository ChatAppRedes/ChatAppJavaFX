package com.chatapp.chatappjavaclient;

import java.io.IOException;
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
    public ClientReceiverTask clientReceiverTask;
    
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
   
    public void handleNewMessage(String username, String message) {
        String finalMessage = username + ": " + message;
        if (message.equals("quit")) {
            finalMessage = username + " saiu!";
        } else if (message.equals("welcome")) {
            finalMessage = "Bem vindo(a), " + username;
        }
        this.chatContentAsString += "\n" + finalMessage;
    }
    
    private void createClientReceiver() {
        this.clientReceiverTask = new ClientReceiverTask(this, App.username);
        this.clientReceiverThread = new Thread(this.clientReceiverTask);
        this.clientReceiverThread.start();
        this.chatContent.textProperty().bind(this.clientReceiverTask.messageProperty());
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        JSONObject messageToServer = new JSONObject();
        messageToServer.put("username", App.username);
        messageToServer.put("message", "quit");
        String finalMessage = messageToServer.toString() + '\n';
        App.serverConnection.outToServer.writeBytes(finalMessage);
        App.setRoot("primary");
    }
    
    @FXML
    private void sendMessageToServer() throws IOException {
        String message = messageField.textProperty().get();
        System.out.println(message);
        if (message.equals("quit")) {
            return;
        }
        JSONObject messageToServer = new JSONObject();
        messageToServer.put("username", App.username);
        messageToServer.put("message", message);
        String finalMessage = messageToServer.toString() + '\n';
        App.serverConnection.outToServer.writeBytes(finalMessage);
        messageField.setText("");
    }
}