package com.heyjie.springboot1.controller;

import java.util.List;

import com.heyjie.springboot1.model.Person;
import com.heyjie.springboot1.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/person")
public class PersonController {
    private PersonRepository personRepository;

    //通过构造函数自动注入BookRepository的动态生成的实现类
    public PersonController(PersonRepository personRepository) {
        super();
        this.personRepository = personRepository;
    }

    @RequestMapping(value="/list")
    public String list(Model model)
    {
        List<Person> persons = personRepository.findAll();
        model.addAttribute("persons", persons);//model中放的数据可以在视图页面中获取到
        return "person/list";
    }

    @RequestMapping(value="/addnew",method=RequestMethod.GET)
    public String addnew()
    {
        return "person/addnew";
    }

    @RequestMapping(value="/addnew",method=RequestMethod.POST)
    public String addnew(Person person)
    {
        personRepository.save(person);
        return "redirect:/person/list";
    }

    @RequestMapping(value="/list/age/{age}",method=RequestMethod.GET)
    public String listByAge(@PathVariable("age") int age, Model model)
    {
        int i=123;
        List<Person> persons = personRepository.findByAge(age);
        model.addAttribute("persons", persons);
        return "person/list";
    }

}

