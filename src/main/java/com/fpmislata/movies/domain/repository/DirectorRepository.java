package com.fpmislata.movies.domain.repository;

import java.util.Optional;

import com.fpmislata.movies.domain.entity.Director;

public interface DirectorRepository {

    int insert(Director director);
    
    void update(Director director);

    Optional<Director> find(int id);
    
    void delete(int id);

    Optional<Director> findByMovieId(int movieId);

}
