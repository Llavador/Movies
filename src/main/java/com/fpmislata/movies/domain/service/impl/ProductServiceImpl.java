package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.domain.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    
    @Override
    public String getAll() {
        return "Listado de todos los productos";
    }
}
