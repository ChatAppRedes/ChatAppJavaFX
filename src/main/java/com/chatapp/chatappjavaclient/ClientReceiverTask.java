/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.chatappjavaclient;

import java.util.function.Function;
import javafx.concurrent.Task;
import org.json.JSONObject;

/**
 *
 * @author ruifernandes
 */
public class ClientReceiverTask extends Task<Void> {

    public boolean isReceiverActive;
    //abstract void notifyNewMessage(String newMessage);

    public IMessageHandler messageHandler;
    
    public ClientReceiverTask(IMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
    
    public void run() {
        try {
            
            ServerConnection clientReceiverConnection = new ServerConnection();

            JSONObject data = new JSONObject();
            data.put("receiver", true);
            String dataString = data.toString();

            clientReceiverConnection.outToServer.writeBytes(dataString + "\n");
            this.isReceiverActive = true;
            
            System.out.println("Start receiver");
            while(this.isReceiverActive) {
                System.out.println("Loop thread");
                String newMessage = clientReceiverConnection.inFromServer.readLine();
                System.out.println("From server: " + newMessage);
                
                this.messageHandler.handleNewMessage(newMessage);
                String allMessages = this.messageHandler.getAllMessages();
                this.updateMessage(allMessages);
            }
        } catch (Exception e) {
            this.isReceiverActive = false;
        }
    }

    @Override
    protected Void call() throws Exception {
        this.run();
        return null;
    }
    
    
}
