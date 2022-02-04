package com.chatapp.chatappjavaclient;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondaryController {

    @FXML
    public Label helloMessageLabel;
    
    @FXML
    public void initialize() {
        System.out.println("Segunda tela");
        helloMessageLabel.setText("Bem-vindo(a), " + App.username);
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
       
    }
}