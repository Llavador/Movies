package com.fpmislata.movies.domain.persistence;

import java.util.Optional;

import com.fpmislata.movies.domain.entity.Actor;

public interface ActorRepository {

    int insert(Actor actor);
    
    Optional<Actor> find(int id);

    void update(Actor actor);

    void delete(int id);
    
}
