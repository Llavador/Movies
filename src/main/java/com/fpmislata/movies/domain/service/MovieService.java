package com.fpmislata.movies.domain.service;

import java.util.List;

import com.fpmislata.movies.domain.entity.CharacterMovie;
import com.fpmislata.movies.domain.entity.Movie;

public interface MovieService {

    List<Movie> getAll(Integer page, Integer pageSize);
    
    List<Movie> getAll();

    Movie find(int id);

    long getTotalNumberOfRecords();
    
    Movie create(Movie movie);
    
    Movie update(Movie movie);
    
    List<Movie> findByDirectorId(int directorId);
    
    void delete(int id);
    
    Movie addCharacterMovie(int id, CharacterMovie characterMovie);

    Movie updateCharacterMovie(int id, CharacterMovie characterMovie);
}