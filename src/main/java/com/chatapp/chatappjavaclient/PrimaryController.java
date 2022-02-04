package com.chatapp.chatappjavaclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
       App.serverConnection = new ServerConnection();
        
       JSONObject data = new JSONObject();
       
       TextField usernameField = (TextField) App.scene.lookup("#usernameField");
       
       String username = usernameField.textProperty().get();
       
       if (username.isEmpty()) {
           username = "Guest";
       }

       data.put("username", username);
       String dataString = data.toString();
       
       App.serverConnection.outToServer.writeBytes(dataString + "\n");
       App.username = username;
       App.setRoot("secondary");
    }
}
