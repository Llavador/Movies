package com.fpmislata.movies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
 
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public void index() {
        System.out.println("Método index del controlador Main ejecutándose");
    }
 
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/about")
    public void about(){
        System.out.println("Método about de MainController ejecutándose");
    }    
}