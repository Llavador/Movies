package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.dto.ActorDTO;

public interface ActorService {

    int create(ActorDTO actorDTO);
    
    void update(ActorDTO actorDTO);

    void delete(int id);

    ActorDTO find(int id);
}
