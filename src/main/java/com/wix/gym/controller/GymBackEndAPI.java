package com.wix.gym.controller;


import com.wix.gym.model.Class;
import com.wix.gym.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GymBackEndAPI {

    @Autowired
    ClassRepository classRepository;


    @GetMapping("/classes")
    public List<Class> getAvailableClasses(){
        return classRepository.findAll();
    }

    @GetMapping("/")
    public String sayHello(){
        return "Hi there";
    }
}
