package com.heyjie.springboot1.repository;

import java.util.List;

import com.heyjie.springboot1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByAge(int age);
    Person findByName(String name);
}

