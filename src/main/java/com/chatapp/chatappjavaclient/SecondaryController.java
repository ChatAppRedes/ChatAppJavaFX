package com.chatapp.chatappjavaclient;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        System.out.println(messageField.textProperty().get());
    }
}