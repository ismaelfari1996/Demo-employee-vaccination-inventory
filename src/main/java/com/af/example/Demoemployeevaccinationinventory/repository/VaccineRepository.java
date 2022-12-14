/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.repository;

import com.af.example.Demoemployeevaccinationinventory.model.Vaccine;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ismael
 */
@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Integer> {
    Optional<Vaccine> findByVaccine(String vaccine);
}
