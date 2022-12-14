/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.utility;

import java.util.HashMap;

/**
 *
 * @author Ismael
 */
public class Messages {
    
    private   HashMap<String, String> messages=new HashMap<>();
    
    public  HashMap<String, String> newMessage(String key, String message){
        this.messages.put(key, message);
        return messages;
    }
}
