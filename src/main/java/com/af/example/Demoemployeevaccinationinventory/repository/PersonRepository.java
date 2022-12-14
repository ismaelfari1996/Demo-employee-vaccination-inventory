/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.repository;

import com.af.example.Demoemployeevaccinationinventory.model.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ismael
 */
@Repository
public interface PersonRepository extends JpaRepository<Person,String>{
    Optional<Person> getByPersonName(String personName);
    
    @Query( value = "SELECT p FROM Person p WHERE  p.isVaccinated=false")
    List <Person> getAllNotVaccinated();
}
