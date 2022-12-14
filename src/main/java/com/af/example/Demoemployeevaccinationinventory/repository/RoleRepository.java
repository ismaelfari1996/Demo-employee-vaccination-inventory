/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.repository;

import com.af.example.Demoemployeevaccinationinventory.enums.RoleName;
import com.af.example.Demoemployeevaccinationinventory.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ismael
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional <Role> getByRoleName(RoleName rolName);
    
}
