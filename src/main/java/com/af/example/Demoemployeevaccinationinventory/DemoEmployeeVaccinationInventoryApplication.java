package com.af.example.Demoemployeevaccinationinventory;

import com.af.example.Demoemployeevaccinationinventory.controller.UserController;
import com.af.example.Demoemployeevaccinationinventory.enums.RoleName;
import com.af.example.Demoemployeevaccinationinventory.model.Person;
import com.af.example.Demoemployeevaccinationinventory.model.Role;
import com.af.example.Demoemployeevaccinationinventory.model.User;
import com.af.example.Demoemployeevaccinationinventory.model.Vaccine;
import com.af.example.Demoemployeevaccinationinventory.service.PersonService;
import com.af.example.Demoemployeevaccinationinventory.service.RoleService;
import com.af.example.Demoemployeevaccinationinventory.service.UserService;
import com.af.example.Demoemployeevaccinationinventory.service.VaccineService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoEmployeeVaccinationInventoryApplication implements CommandLineRunner {
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private VaccineService vaccineService;
    
    private static Logger logger= LoggerFactory.getLogger(UserController.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoEmployeeVaccinationInventoryApplication.class, args);
	}
        
    /*    @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }*/

    @Override
    public void run(String... args) throws Exception {
        if(roleService.findAll().isEmpty()){
            Role role=new Role(RoleName.ADMIN, "Super usuario");
            roleService.save(role);
            Role role1=new Role(RoleName.EMPLEADO,"Permisos restringidos");
            roleService.save(role1);
            logger.info("Se crearon los roles: ADMIN, EMPLEADO");
        }
        if(personService.findAll().isEmpty()){
            Person person=new Person("9999999999", "Admin", "Admin", "admin@hotmail.com", "", "", "", false);
            personService.save(person);
            User user= new User("admin",passwordEncoder.encode("admin"), person);
            Set<Role> role=new HashSet<>();
            role.add(roleService.getByRoleName(RoleName.ADMIN).get());
            user.setRoles(role);
            userService.save(user);
            logger.info("Se creo el usuario Administrador, usuario: admin \t password: admin");
            logger.warn("Las credenciales por defecto deben ser actualizados por seguridad");
        }
        
        if(vaccineService.findAll().isEmpty()){
            Vaccine v1=new Vaccine("Sputnik", "Ninguno");
            vaccineService.save(v1);
            Vaccine v2=new Vaccine("AstraZeneca", "Ninguno");
            vaccineService.save(v2);
            Vaccine v3=new Vaccine("Pfizer", "Ninguno");
            vaccineService.save(v3);
            Vaccine v4=new Vaccine("Jhonson&Jhonson", "Ninguno");
            vaccineService.save(v4);
            
        }
    }

}
