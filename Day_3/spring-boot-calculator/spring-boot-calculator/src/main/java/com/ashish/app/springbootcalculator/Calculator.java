package com.ashish.app.springbootcalculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Calculator {

    @GetMapping("/add")
    public float addition(){
        return 5;
    }
    @GetMapping("/sub")
    public float subtraction(){
        return 3;
    }
    @GetMapping("/mul")
    public float multiplication(){
        return 12;
    }
    @GetMapping("/div")
    public float division(){
        return 4;
    }

}