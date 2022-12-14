/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.controller;

import com.af.example.Demoemployeevaccinationinventory.dto.VaccinationDetailDto;
import com.af.example.Demoemployeevaccinationinventory.model.Person;
import com.af.example.Demoemployeevaccinationinventory.model.VaccinationDetail;
import com.af.example.Demoemployeevaccinationinventory.model.Vaccine;
import com.af.example.Demoemployeevaccinationinventory.service.VaccinationDetailsService;
import com.af.example.Demoemployeevaccinationinventory.service.VaccineService;
import com.af.example.Demoemployeevaccinationinventory.utility.Messages;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class VaccinationDetailController {
    private static Logger logger= LoggerFactory.getLogger(VaccinationDetailController.class);
    @Autowired
    private VaccinationDetailsService vaccinationDetailService;
    
    @Autowired
    private VaccineService vaccineService;
    
    @GetMapping("/private/admin/vaccinated-list")
    private ResponseEntity<List<VaccinationDetail>> getAllVaccinatedList(){
        return ResponseEntity.ok(vaccinationDetailService.findAll());
    }
    
    @GetMapping("/private/employee/get-vaccination-detail/{id}")
    private ResponseEntity<?> getByIdPersonEmployee(@PathVariable("id") String id){
        Person person=new Person();
        person.setIdentityCard(id);
        logger.info("Parametro de entrada: {}",person.getIdentityCard());
        return ResponseEntity.ok(vaccinationDetailService.findByPersonId(person));
    }
    
    @GetMapping("/private/admin/get-vaccination-detail/{id}")
    private ResponseEntity<?> getByIdPersonAdmin(@PathVariable("id") String id){
        Person person=new Person();
        person.setIdentityCard(id);
        logger.info("Parametro de entrada: {}",person.getIdentityCard());
        return ResponseEntity.ok(vaccinationDetailService.findByPersonId(person));
    }
    
    @GetMapping("/private/admin/by-vaccine-name/{vaccineName}")
    private ResponseEntity<List<VaccinationDetail>> getByVaccineName(@PathVariable("vaccineName") String vaccineName){
        return ResponseEntity.ok(vaccinationDetailService.getByVaccineName(vaccineName));
    }
    
    @GetMapping("/private/admin/by-vaccination-range/{start}/{end}")
    private ResponseEntity<List<VaccinationDetail>> getByVaccinationDateRange(@PathVariable("start") String startDate,@PathVariable("end") String endDate){
        return ResponseEntity.ok(vaccinationDetailService.getByDateRange(startDate, endDate));
    }
    
    @PostMapping("/private/employee/vaccination-detail")
    private ResponseEntity<?> save(@RequestBody VaccinationDetailDto newVaccinationDetail){
        VaccinationDetail vaccinationDetail=new VaccinationDetail(newVaccinationDetail.getVaccinationDate(),
                newVaccinationDetail.getNumberDoses(), newVaccinationDetail.getPerson());
        Set<Vaccine> vaccine=new HashSet<>();
        newVaccinationDetail.getVaccine().forEach((vaccineName)->{
            vaccine.add( vaccineService.findByVaccine(vaccineName).get());
         }
        );
        vaccinationDetail.setVaccine(vaccine);
        vaccinationDetailService.save(vaccinationDetail);
        return new ResponseEntity(new Messages().newMessage("INFO", "Detalle guardado")
                    ,HttpStatus.CREATED);
    }
    
    @PutMapping("/private/employee/update-vaccination-detail")
    private ResponseEntity<?> update( @RequestBody VaccinationDetailDto updateVaccineDetail){
        logger.error("Numero de id: "+ updateVaccineDetail.getId());
        VaccinationDetail updateDetail=vaccinationDetailService.getOne(updateVaccineDetail.getId());
        updateDetail.setNumberDoses(updateVaccineDetail.getNumberDoses());
        updateDetail.setVaccinationDate(updateVaccineDetail.getVaccinationDate());
        Set<Vaccine> updateVaccine=new HashSet<>();
        updateVaccineDetail.getVaccine().forEach((vaccineName)->{
            updateVaccine.add(vaccineService.findByVaccine(vaccineName).get());
        });
        updateDetail.setVaccine(updateVaccine);
        vaccinationDetailService.save(updateDetail);
        return new ResponseEntity(new Messages().newMessage("INFO", "Datos actualizados"),HttpStatus.OK);
    }
    
    @PutMapping("/private/admin/update-vaccination-detail")
    private ResponseEntity<?> updateVaccinationDetailEmployee( @RequestBody VaccinationDetailDto updateVaccineDetail){
        logger.error("Numero de id: "+ updateVaccineDetail.getId());
        VaccinationDetail updateDetail=vaccinationDetailService.getOne(updateVaccineDetail.getId());
        updateDetail.setNumberDoses(updateVaccineDetail.getNumberDoses());
        updateDetail.setVaccinationDate(updateVaccineDetail.getVaccinationDate());
        Set<Vaccine> updateVaccine=new HashSet<>();
        updateVaccineDetail.getVaccine().forEach((vaccineName)->{
            updateVaccine.add(vaccineService.findByVaccine(vaccineName).get());
        });
        updateDetail.setVaccine(updateVaccine);
        vaccinationDetailService.save(updateDetail);
        return new ResponseEntity(new Messages().newMessage("INFO", "Datos actualizados"),HttpStatus.OK);
    }
    
    
    
    @DeleteMapping("/private/admin/delete-detail-by-id/{id}")
    private ResponseEntity<?> deleteById(@PathVariable("id") @RequestBody int id){
        logger.error("Intentando elimiar...");
        vaccinationDetailService.deleteById(id);
        if(vaccinationDetailService.existsById(id)){
            return new ResponseEntity(new Messages().newMessage("ERROR","No se elimino el registro"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new Messages().newMessage("INFO","Registro eliminado correctamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("/private/employee/delete-detail-by-id/{id}")
    private ResponseEntity<?> deleteDetailById(@PathVariable("id") @RequestBody int id){
        logger.error("Intentando elimiar...");
        vaccinationDetailService.deleteById(id);
        if(vaccinationDetailService.existsById(id)){
            return new ResponseEntity(new Messages().newMessage("ERROR","No se elimino el registro"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new Messages().newMessage("INFO","Registro eliminado correctamente"), HttpStatus.OK);
    }
    
    
}
