/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.chatappjavaclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author ruifernandes
 */
public class ServerConnection {
    public Socket clientSocket;
    public DataOutputStream outToServer;
    public BufferedReader inFromServer;
    
    public ServerConnection() throws IOException {
       this.clientSocket = new Socket("localhost", 3335);
       this.outToServer = new DataOutputStream(this.clientSocket.getOutputStream());
       this.inFromServer = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
    }
}
