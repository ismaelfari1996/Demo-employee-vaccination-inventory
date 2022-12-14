/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.controller;

import com.af.example.Demoemployeevaccinationinventory.dto.JwtDto;
import com.af.example.Demoemployeevaccinationinventory.dto.LoginUser;
import com.af.example.Demoemployeevaccinationinventory.securityconfig.JwtProvider;
import com.af.example.Demoemployeevaccinationinventory.service.UserService;
import com.af.example.Demoemployeevaccinationinventory.utility.Messages;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ismael
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private UserService userService;
    
    @Autowired
    AuthenticationManager authenticationMaanager;
    
    
    @Autowired
    JwtProvider jwtProvider;
    
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);
    
    @PostMapping("/public/log-in")
    private ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser){
        if(userService.existsByUserName(loginUser.getUserName())){
            try{
            Authentication authentication=
                    authenticationMaanager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getUserPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt=jwtProvider.generateToken(authentication);
            UserDetails userDetails=(UserDetails)authentication.getPrincipal(); 
            JwtDto jwtDto=new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
            return new ResponseEntity(jwtDto,HttpStatus.OK);
            }catch(AuthenticationException e){
                return new ResponseEntity(new Messages().newMessage("ERROR","Credenciales incorrecta"),HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity(new Messages().newMessage("ERROR", "Usuario "+loginUser.getUserName()+" no existe"),HttpStatus.BAD_REQUEST);
        }
    }
}
