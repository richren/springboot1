package com.heyjie.springboot1.controller;

import com.heyjie.springboot1.entity.Person;
import com.heyjie.springboot1.dto.PersonDto;
import com.heyjie.springboot1.mapper.PersonMapper;
import com.heyjie.springboot1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class RESTfulController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/person/addnew", method = RequestMethod.POST)
    public long addnew(String name, Integer age, HttpServletResponse response) throws IOException {
        if (personRepository.existsByName(name)) {
            response.sendError(HttpStatus.CONFLICT.value(), "用户名已经存在");
            return 0;
        }
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        personRepository.save(person);
        return person.getId();
    }

    @RequestMapping(value = "/person/add-model", method = RequestMethod.POST)
    public long addnew1(PersonDto dto, HttpServletResponse response) throws IOException {
        if (personRepository.existsByName(dto.getName())) {
            response.sendError(HttpStatus.CONFLICT.value(), "用户名已经存在");
            return 0;
        }
        Person person = PersonMapper.MAPPER.toEntity(dto);
        personRepository.save(person);
        return person.getId();
    }

    //这个请求要用json格式请求，设置请求的contentType=application/json
    @RequestMapping(value = "/person/add-body", method = RequestMethod.POST)
    public long addnew2(@RequestBody PersonDto dto, HttpServletResponse response) throws IOException {
        if (personRepository.existsByName(dto.getName())) {
            response.sendError(HttpStatus.CONFLICT.value(), "用户名已经存在");
            return 0;
        }
        Person person = PersonMapper.MAPPER.toEntity(dto);
        personRepository.save(person);
        return person.getId();
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getById(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            return personOptional.get();
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "用户不存在");
            return null;
        }
    }

    @RequestMapping(value = "/person/delete/{id}", method = RequestMethod.DELETE)
    public void disable(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            personRepository.delete(person);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "用户不存在");
        }
    }

}
