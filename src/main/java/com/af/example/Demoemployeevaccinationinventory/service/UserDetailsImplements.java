/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.service;

import com.af.example.Demoemployeevaccinationinventory.model.MainUser;
import com.af.example.Demoemployeevaccinationinventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ismael
 */
@Service
public class UserDetailsImplements implements UserDetailsService{
    
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.findByUserName(username).get();
        return MainUser.build(user);
    }
    
//    @Bean
//    public UserService userService(){
//        return new UserService();
//    }
}
