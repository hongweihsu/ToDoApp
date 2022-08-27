package com.todoapp.hongwei.todoapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AppController {

    @Value("${spring.application.name}")
    private String name;

    @GetMapping("/")
    public String getRoot(){
        log.info(name);
        return "Hello World";
    }
}
