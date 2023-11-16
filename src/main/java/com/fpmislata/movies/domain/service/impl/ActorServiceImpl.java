package com.fpmislata.movies.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.service.ActorService;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.persistence.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {
    
    @Autowired
    ActorRepository actorRepository;

    @Override
    public int create(Actor actor) {
        return actorRepository.insert(actor);
    }

    @Override
    public void update(Actor actor) {
        Actor existingActor = actorRepository.find(actor.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + actor.getId()));
        actorRepository.update(actor);
    }

    @Override
    public void delete(int id) {
        Actor actor = actorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + id));
        actorRepository.delete(id);
    }
}
