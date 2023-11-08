package com.fpmislata.movies.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
 
    @GetMapping("/")
    public void index() {
        System.out.println("Método index del controlador Main ejecutándose");
    }
 
    @GetMapping("/about")
    public void about(){
        System.out.println("Método about de MainController ejecutándose");
    }    
}