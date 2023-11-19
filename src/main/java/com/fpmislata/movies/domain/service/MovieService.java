package com.fpmislata.movies.domain.service;

import java.util.List;

import com.fpmislata.movies.domain.entity.Movie;

public interface MovieService {

    List<Movie> getAll(Integer page, Integer pageSize);
    
    List<Movie> getAll();

    Movie find(int id);

    int getTotalNumberOfRecords();
    
    int create(Movie movie, int directorId, List<Integer> actorIds);
    
}
