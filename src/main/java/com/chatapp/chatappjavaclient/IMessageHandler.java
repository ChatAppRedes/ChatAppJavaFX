/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatapp.chatappjavaclient;

/**
 *
 * @author ruifernandes
 */
public interface IMessageHandler {
    public String getAllMessages();
    public void handleNewMessage(String username, String message);
}
