package com.fpmislata.movies.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.service.ActorService;
import com.fpmislata.movies.persistence.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {
    
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public void create(Actor actor) {
        actorRepository.insert(actor);
    }

    
}
