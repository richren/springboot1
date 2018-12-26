package com.heyjie.springboot1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {
    @RequestMapping(value = "/csrf", method = RequestMethod.GET)
    public String csrf() {
        return "/main/csrf";
    }

    @RequestMapping(value = "/csrf", method = RequestMethod.POST)
    public String test(String name) {
        System.out.println("name=" + name);
        return "/main/csrf";
    }
}
