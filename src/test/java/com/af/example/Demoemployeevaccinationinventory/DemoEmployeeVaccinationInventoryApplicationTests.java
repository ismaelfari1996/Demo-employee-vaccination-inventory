package com.af.example.Demoemployeevaccinationinventory;

import com.af.example.Demoemployeevaccinationinventory.model.Person;
import com.af.example.Demoemployeevaccinationinventory.service.PersonService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoEmployeeVaccinationInventoryApplicationTests {
    @Autowired
    private PersonService personService;
	@Test
	void contextLoads() {
            Person person=new Person("9999999999", "admin", "Super ususario", "admin@hotmail.com", "", "", "", false);
            personService.save(person);
            assertEquals(true,personService.existsById("9999999999"));
	}

}
