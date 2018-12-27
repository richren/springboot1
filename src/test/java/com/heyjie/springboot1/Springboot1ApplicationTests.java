package com.heyjie.springboot1;

import com.heyjie.springboot1.config.HeyjieConfig;
import com.heyjie.springboot1.model.Person;
import com.heyjie.springboot1.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot1ApplicationTests {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	HeyjieConfig heyjieConfig;

	@Test
	public void contextLoads() {
		List<Person> persons = personRepository.findAll();
		System.out.println(persons);
	}

	@Test
	public void config() {
		System.out.println(heyjieConfig.getUsername());
		System.out.println(heyjieConfig.getPassword());
	}
}

