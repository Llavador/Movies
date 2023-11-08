package com.fpmislata.movies.controller;

import com.fpmislata.movies.domain.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
@RequestMapping("/products")
@RestController
public class ProductController {
 
    @Autowired
    ProductService productService;
         
        @GetMapping("")
        public void getAll(){
            System.out.println(productService.getAll());
        }
 
        @GetMapping("/{id}")
        public void getById(@PathVariable("id") int id){
            System.out.println("Ruta: /productos/" + id);
        }
}