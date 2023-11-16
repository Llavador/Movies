package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Actor;

public interface ActorService {

    int create(Actor actor);
    
    void update(Actor actor);

    void delete(int id);
    
}
