package com.chatapp.chatappjavaclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import org.json.JSONObject;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static String username;
    public static ServerConnection serverConnection;
    
    public static Thread clientReceiverThread;
    public static ClientReceiverTask clientReceiverTask;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() throws IOException {
        System.out.println("Quitting app");
        JSONObject messageToServer = new JSONObject();
        messageToServer.put("username", App.username);
        messageToServer.put("message", "quit");
        String finalMessage = messageToServer.toString() + '\n';
        App.serverConnection.outToServer.writeBytes(finalMessage);
        System.out.println("Finish");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}