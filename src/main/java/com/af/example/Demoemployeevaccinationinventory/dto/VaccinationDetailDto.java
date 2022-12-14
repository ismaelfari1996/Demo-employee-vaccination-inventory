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
public class VaccinationDetailDto {
    
    private int id;
    
    @NotBlank
    private String vaccinationDate;
    @NotBlank
    private int numberDoses; 
    @NotBlank
    private Person person;
    @NotBlank
    private Set<String> vaccine=new HashSet<>();

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public int getNumberDoses() {
        return numberDoses;
    }

    public void setNumberDoses(int numberDoses) {
        this.numberDoses = numberDoses;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<String> getVaccine() {
        return vaccine;
    }

    public void setVaccine(Set<String> vaccine) {
        this.vaccine = vaccine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
