package com.fpmislata.movies.persistence;

import java.util.List;

import com.fpmislata.movies.domain.entity.Movie;

public interface MovieRepository {

    List<Movie> getAll(Integer page, Integer pageSize);

    Movie find(int id);
    
    int getTotalNumberOfRecords();
}
