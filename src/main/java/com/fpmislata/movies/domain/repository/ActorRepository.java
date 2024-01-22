package com.fpmislata.movies.domain.repository;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.domain.entity.Actor;

public interface ActorRepository {

    int insert(Actor actor);
    
    Optional<Actor> find(int id);

    List<Actor> findByMovieId(int movieId);

    void update(Actor actor);

    void delete(int id);
    
}
