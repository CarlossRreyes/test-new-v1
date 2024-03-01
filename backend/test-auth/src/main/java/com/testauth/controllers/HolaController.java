package com.testauth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administration")
public class HolaController {


    @GetMapping("/hola")
    public String  initApp(){
        return "Hola Mundo, que Dios nos ayude JEJE";
    }
    
}
