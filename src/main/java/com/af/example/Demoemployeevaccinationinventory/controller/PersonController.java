/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.controller;

import com.af.example.Demoemployeevaccinationinventory.enums.RoleName;
import com.af.example.Demoemployeevaccinationinventory.model.Person;
import com.af.example.Demoemployeevaccinationinventory.model.Role;
import com.af.example.Demoemployeevaccinationinventory.model.User;
import com.af.example.Demoemployeevaccinationinventory.service.PersonService;
import com.af.example.Demoemployeevaccinationinventory.service.RoleService;
import com.af.example.Demoemployeevaccinationinventory.service.UserService;
import com.af.example.Demoemployeevaccinationinventory.utility.GenerateUserName;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.af.example.Demoemployeevaccinationinventory.utility.IdentityCardValidator.isValidIdentityCard;
import com.af.example.Demoemployeevaccinationinventory.utility.Messages;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Ismael
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PersonController {
    
    private static Logger logger= LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/private/admin/person-list")
    private ResponseEntity<List<Person>> getAllPerson(){
        return ResponseEntity.ok(personService.findAll());
    }
    
    @GetMapping("/private/admin/employee-unvaccinated")
    private ResponseEntity<List<Person>> getByUnvaccinated(){
        return ResponseEntity.ok(personService.getAllNotVaccinated());
    } 
    
    @PostMapping("/private/admin/save-employee")
    private ResponseEntity<Person> newPerson(@RequestBody Person person){
        if(!isValidIdentityCard(person.getIdentityCard())){
            return new ResponseEntity(
                    new Messages().newMessage("Error","Cedula no valida"),
                    HttpStatus.BAD_REQUEST);
        }
        if(personService.existsById(person.getIdentityCard()))
            return new ResponseEntity(
                    new Messages().newMessage("Error","Ya existe una persona registrada"),
                    HttpStatus.BAD_REQUEST);
        
        personService.save(person);
         String userName=new GenerateUserName().generateUser(person.getPersonLastName(),
                person.getPersonName(), person.getIdentityCard());
        saveNewUser(userName,person.getIdentityCard(), person);
        return new ResponseEntity(
                new Messages().newMessage("INFO", "Registro guardado correctamente. Usuario: "+userName+" , password: "+person.getIdentityCard()),
                HttpStatus.CREATED);
    }
    
    @PutMapping("/private/employee/update-information")
    private ResponseEntity<?> updateEmployee( @RequestBody Person updatePerson){
        Person person=personService.getOne(updatePerson.getIdentityCard());
        person.setAddress(updatePerson.getAddress());
        person.setBirthday(updatePerson.getBirthday());
        person.setPhoneNumber(updatePerson.getPhoneNumber());
        person.setIsVaccinated(updatePerson.isIsVaccinated());
        
        personService.save(person);
        
        return new ResponseEntity(new Messages().newMessage("INFO", "Registro actualizado con exito"),
                HttpStatus.CREATED);
        
    }
    
    @PutMapping("/private/admin/update-employee-information")
    private ResponseEntity<?> updateEmployeeInformation(@RequestBody Person updatePerson){
       try{
        Person person=personService.getOne(updatePerson.getIdentityCard());
        person.setPersonName(updatePerson.getPersonName());
        person.setPersonLastName(updatePerson.getPersonLastName());
        person.setEmail(updatePerson.getEmail());
        person.setAddress(updatePerson.getAddress());
        person.setBirthday(updatePerson.getBirthday());
        person.setPhoneNumber(updatePerson.getPhoneNumber());
        person.setIsVaccinated(updatePerson.isIsVaccinated());
        personService.save(person);
       }catch(Exception e){
           logger.error(e.getMessage());
       }
        
        return new ResponseEntity(new Messages().newMessage("INFO", "Informacion actualizado con exito"),
                HttpStatus.CREATED);
    }
    
    @PutMapping("/private/admin/update-vaccination-state")
    private ResponseEntity<?> updateVaccinationState(@RequestBody Person update){
        Person person=personService.getOne(update.getIdentityCard());
        person.setIsVaccinated(update.isIsVaccinated());
        personService.save(person);
        return new ResponseEntity("ok",HttpStatus.OK);
    }
    
    @GetMapping("/private/employee/get-information/{identityCard}")
    private ResponseEntity<?> getById(@PathVariable("identityCard") @RequestBody String indentityCard){
        return ResponseEntity.ok(personService.findById(indentityCard));
    }
    
    private void saveNewUser(String userName,String password, Person person){
         User user=new User(userName, passwordEncoder.encode(password), person);
        Set<Role> roles=new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.EMPLEADO).get());
        user.setRoles(roles);
        userService.save(user);
    }
    
    @DeleteMapping("/private/admin/delete-by-id/{id}")
    private ResponseEntity<?> deleteById(@PathVariable("id") @RequestBody String identitycard){
        personService.deleteById(identitycard);
        if(personService.existsById(identitycard)){
            return new ResponseEntity(new Messages().newMessage("ERROR", "No se pudo borrar el registro"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new Messages().newMessage("INFO", "Registro eliminado correctamente"),HttpStatus.OK);
    }
    
   
    
    
}
