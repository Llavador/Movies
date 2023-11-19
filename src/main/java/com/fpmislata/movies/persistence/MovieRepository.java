package com.fpmislata.movies.persistence;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.domain.entity.Movie;

public interface MovieRepository {

    List<Movie> getAll(Integer page, Integer pageSize);

    Optional<Movie> find(int id);
    
    int getTotalNumberOfRecords();
}
