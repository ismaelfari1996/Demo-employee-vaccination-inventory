/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ismael
 */
@Entity
public class VaccinationDetail {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVaccinationDetail;
    @Column(nullable = false) 
    private String vaccinationDate;
     @Column(nullable = false)
    private int numberDoses;
     
    @ManyToOne//(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idPerson")
    private Person person;
    
    @ManyToMany(fetch = FetchType.EAGER)//, cascade = CascadeType.REMOVE)
    @JoinTable(name = "detailVaccine", joinColumns = @JoinColumn(name = "idVaccinationDetail"
    ), inverseJoinColumns = @JoinColumn(name = "idVaccine"))
    private Set<Vaccine> vaccine=new HashSet<>();

    public VaccinationDetail() {
    }

    public VaccinationDetail(String vaccinationDate, int numberDoses, Person person) {
        this.vaccinationDate = vaccinationDate;
        this.numberDoses = numberDoses;
        this.person = person;
    }

    public int getIdVaccinationDetail() {
        return idVaccinationDetail;
    }

    public void setIdVaccinationDetail(int idVaccinationDetail) {
        this.idVaccinationDetail = idVaccinationDetail;
    }

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

    public Set<Vaccine> getVaccine() {
        return vaccine;
    }

    public void setVaccine(Set<Vaccine> vaccine) {
        this.vaccine = vaccine;
    }
    
    
    
    
}
