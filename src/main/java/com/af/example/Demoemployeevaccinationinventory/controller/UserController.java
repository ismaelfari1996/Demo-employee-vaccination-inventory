/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.controller;

import com.af.example.Demoemployeevaccinationinventory.dto.NewUser;
import com.af.example.Demoemployeevaccinationinventory.enums.RoleName;
import com.af.example.Demoemployeevaccinationinventory.model.Role;
import com.af.example.Demoemployeevaccinationinventory.model.User;
import com.af.example.Demoemployeevaccinationinventory.service.RoleService;
import com.af.example.Demoemployeevaccinationinventory.service.UserService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class UserController {
    private static Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    
//    @Autowired
//    AuthenticationManager authenticationMaanager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @PostMapping("/admin/new-user")
    private ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        User user=new User(newUser.getUserName(), passwordEncoder.encode(newUser.getUserPassword()), newUser.getPerson());
        Set<Role> roles=new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.EMPLEADO).get());
        if(newUser.getRoles().contains("admin")){
            roles.add(roleService.getByRoleName(RoleName.ADMIN).get());
        }
        user.setRoles(roles);
        logger.info("Permisos: {}", newUser.getRoles());
        userService.save(user);
        HashMap<String, String> msg=new HashMap<>();
        msg.put("msg", "Usuario creado");
      return new ResponseEntity(msg,HttpStatus.CREATED);
    }
    
    @GetMapping("/private/admin/list-users")
    private ResponseEntity<List<User>> getUsers(){
       return ResponseEntity.ok(userService.findAll());
    }
    
    
    @DeleteMapping("/admin/delete-user/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
        return ResponseEntity.ok(userService.findAll());
    }
    
    @GetMapping("/private/employee/get-user-information/{username}")
    private ResponseEntity<?> getByUserName(@PathVariable("username") @RequestBody String userName){
        return ResponseEntity.ok(userService.findByUserName(userName));
    }
    
}
