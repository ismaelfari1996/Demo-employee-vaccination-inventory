/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.controller;

import com.af.example.Demoemployeevaccinationinventory.model.Person;
import com.af.example.Demoemployeevaccinationinventory.service.PersonService;
import com.af.example.Demoemployeevaccinationinventory.service.UserService;
import com.af.example.Demoemployeevaccinationinventory.service.VaccinationDetailsService;
import com.af.example.Demoemployeevaccinationinventory.utility.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class DeleteController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private VaccinationDetailsService  vaccinationDetailService;
    
    @Autowired
    private PersonService personService;
    
     private static Logger logger= LoggerFactory.getLogger(DeleteController.class);
     
    @DeleteMapping("/private/admin/delete-information/{identitycard}") 
    private ResponseEntity<?> deleteAllInformation(@PathVariable("identitycard") @RequestBody String identitycard){
         logger.error("Cedula: {}",identitycard);
        try{
            Person person=new Person();
            person.setIdentityCard(identitycard);
           
        userService.deleteByIdentityCard(identitycard);
        vaccinationDetailService.deleteByIdentityCard(identitycard);
        personService.deleteById(identitycard);
        }catch(Exception E){
            
            return new ResponseEntity(new Messages().newMessage("ERROR", E.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new Messages().newMessage("INFO", "Registro eliminado correctamente"),
                    HttpStatus.OK);
    }
}
