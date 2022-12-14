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
public  class TokenError {
   private static HashMap<String,String> tokenError=new HashMap<>();
   
   
   
    public static HashMap<String, String> getTokenError() {
        return tokenError;
    }
    
    public static void setTokenError(String key, String msg) {
        HashMap<String, String> error=new HashMap<>();
        error.put(key, msg);
        TokenError.tokenError = error;
    }

   
    
}
