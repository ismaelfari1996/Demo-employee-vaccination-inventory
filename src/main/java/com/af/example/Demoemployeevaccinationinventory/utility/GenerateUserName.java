/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.utility;

/**
 *
 * @author Ismael
 */
public class GenerateUserName {
    private String userName;
    private String lastName[];
    private String name[];
    
    public String generateUser(String lastName, String name,String identityCard){
        lastName=lastName.toLowerCase();
        name=name.toLowerCase();
        this.lastName=lastName.split(" ");
        this.name=name.split(" ");
        if(this.lastName.length==2 && this.name.length==2){
            userName=name.charAt(0)+this.lastName[0]+this.lastName[1].charAt(0)+getSubString(identityCard);
        } else{
             userName=name.charAt(0)+this.lastName[0]+getSubString(identityCard);
        }
        return userName;
    }
    
    private String getSubString(String identityCard){
        return identityCard.substring(6, identityCard.length()-1);
    }
}
