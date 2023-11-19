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
    MovieService movieService;
 
    public static final String MOVIES = "/movies";
    
    @Value("${default.pageSize}")
    private int defaultPageSize;
    
    @Value("${application.url}")
    private String urlBase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : defaultPageSize;
        List<Movie> movies = (page != null)? movieService.getAll(page, pageSize) : movieService.getAll();
        int totalRecords = movieService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(movies)
                .totalRecords(totalRecords)
                .build();
        if(page != null) {
            response.paginate(page, pageSize, urlBase);
        }
            return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        Movie movie = movieService.find(id);
        Response response = Response.builder()
            .data(movie)
            .build();
        return response;
    }
}