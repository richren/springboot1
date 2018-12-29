package com.heyjie.springboot1.controller;

import com.heyjie.springboot1.common.model.response.DataResponse;
import com.heyjie.springboot1.common.model.response.DataResponseCode;
import com.heyjie.springboot1.dto.PersonDto;
import com.heyjie.springboot1.entity.Person;
import com.heyjie.springboot1.mapper.PersonMapper;
import com.heyjie.springboot1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/response")
public class ResponseController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/person/addnew", method = RequestMethod.POST)
    public DataResponse<Long> addnew(String name, Integer age, HttpServletResponse response) throws IOException {
        if (personRepository.existsByName(name)) {
            return new DataResponse<>(DataResponseCode.RECORD_ALREADY_EXISTS);
        }
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        personRepository.save(person);
        return new DataResponse<>(person.getId());
    }

    @RequestMapping(value = "/person/add-model", method = RequestMethod.POST)
    public DataResponse<Long> addnew1(PersonDto dto, HttpServletResponse response) throws IOException {
        if (personRepository.existsByName(dto.getName())) {
            return new DataResponse<>(DataResponseCode.RECORD_ALREADY_EXISTS);
        }
        Person person = PersonMapper.MAPPER.toEntity(dto);
        personRepository.save(person);
        return new DataResponse<>(person.getId());
    }

    //这个请求要用json格式请求，设置请求的contentType=application/json
    @RequestMapping(value = "/person/add-body", method = RequestMethod.POST)
    public DataResponse<Long> addnew2(@RequestBody PersonDto dto, HttpServletResponse response) throws IOException {
        if (personRepository.existsByName(dto.getName())) {
            return new DataResponse<>(DataResponseCode.RECORD_ALREADY_EXISTS);
        }
        Person person = PersonMapper.MAPPER.toEntity(dto);
        personRepository.save(person);
        return new DataResponse<>(person.getId());
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public DataResponse<PersonDto> getById(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            return new DataResponse<>(PersonMapper.MAPPER.toDto(personOptional.get()));
        } else {
            return new DataResponse<>(DataResponseCode.RECORD_DOES_NOT_EXISTS);
        }
    }

    @RequestMapping(value = "/person/delete/{id}", method = RequestMethod.DELETE)
    public DataResponse disable(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            personRepository.delete(person);
        } else {
            return new DataResponse<>(DataResponseCode.RECORD_DOES_NOT_EXISTS);
        }
        return new DataResponse("删除成功！");
    }
}
