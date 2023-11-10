package com.fpmislata.movies.persistence;

import java.util.List;

import com.fpmislata.movies.domain.entity.Movie;

public interface MovieRepository {

    List<Movie> getAll();

    Movie find(int id);
    
}
