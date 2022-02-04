package com.chatapp.chatappjavaclient;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class SecondaryController {

    @FXML
    public Label helloMessageLabel;
    
    @FXML
    public TextField messageField;
    
    
    @FXML
    public void initialize() {
        System.out.println("Segunda tela");
        helloMessageLabel.setText("Bem-vindo(a), " + App.username);
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
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