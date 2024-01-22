package com.fpmislata.movies.domain.repository;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.domain.entity.Movie;

public interface MovieRepository {

    List<Movie> getAll(Integer page, Integer pageSize);

    Optional<Movie> find(int id);
    
    long getTotalNumberOfRecords();

    Movie insert(Movie movie);

    Movie update(Movie movie);
    
    List<Movie> findByDirectorId(int directorId);

    void delete(Movie movie);
}
