/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.repository;

import com.af.example.Demoemployeevaccinationinventory.model.Person;
import com.af.example.Demoemployeevaccinationinventory.model.VaccinationDetail;
import com.af.example.Demoemployeevaccinationinventory.model.Vaccine;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ismael
 */
public interface VaccinationDetailRepository extends JpaRepository<VaccinationDetail,Integer> {
    @Query("SELECT vd FROM VaccinationDetail vd WHERE vd.person=:id")
    Optional<VaccinationDetail> findByPersonId(@Param("id")Person idPerson);
    
    @Query(value="SELECT vd FROM VaccinationDetail vd LEFT JOIN vd.vaccine v WHERE v.vaccine=:vaccine")
    List<VaccinationDetail> getByVaccineName(@Param("vaccine") String vaccineName);
    
    @Query(value="SELECT vd FROM VaccinationDetail vd WHERE vd.vaccinationDate>=:start AND  vd.vaccinationDate<=:end")
    List<VaccinationDetail> getByDateRange(@Param("start")String startDate, @Param("end") String endDate);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM VaccinationDetail vd WHERE vd.person.identityCard=:id")
    void deleteByIdentityCard(@Param("id") String identityCard);
}
