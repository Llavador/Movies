package com.fpmislata.movies.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@RequestMapping("/directors")
@RestController
public class DirectorController {
 
    @Autowired
    DirectorService directorService;
 
    Director director = new Director("Joss Whedon", 1964, null);
 
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/insert")
    public void create(){
        try {
            directorService.create(this.director);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
