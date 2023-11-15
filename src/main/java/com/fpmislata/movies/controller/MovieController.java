package com.fpmislata.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.http_response.Response;

import java.util.List;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Value("${default.pageSize}")
    private int defaultPageSize;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : defaultPageSize;
        List<Movie> movies = (page != null)? movieService.getAll(page, pageSize) : movieService.getAll();
        int totalRecords = movieService.getTotalNumberOfRecords();
        if(page != null) {
            return new Response(movies, totalRecords, page, pageSize);
        } else {
            return new Response(movies, totalRecords);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Movie find(@PathVariable("id") int id) {
        try {
            System.out.println(movieService.find(id));
            return movieService.find(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}