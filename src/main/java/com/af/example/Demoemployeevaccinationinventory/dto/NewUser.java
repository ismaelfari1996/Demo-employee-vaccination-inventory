/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.dto;

import com.af.example.Demoemployeevaccinationinventory.model.Person;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Ismael
 */
public class NewUser {
    @NotBlank
    private String userName;
    @NotBlank
    private String userPassword;
    @NotBlank
    private Person person;
    
    private Set<String> roles=new HashSet<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
    
    
}
