/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.repository;

import com.af.example.Demoemployeevaccinationinventory.model.Person;
import com.af.example.Demoemployeevaccinationinventory.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ismael
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.person.identityCard=:id")
    void deleteByIdentityCard(@Param("id") String identityCard);
}
