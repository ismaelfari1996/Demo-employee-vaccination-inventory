/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.controller;

import com.af.example.Demoemployeevaccinationinventory.model.Vaccine;
import com.af.example.Demoemployeevaccinationinventory.service.VaccineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ismael
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class VaccineController {
    @Autowired
    private VaccineService vaccineService;
    
    @GetMapping("/public/vaccine")
    private ResponseEntity<List<Vaccine>> getAllVacine(){
        return ResponseEntity.ok(vaccineService.findAll());
    }
    
    
}
