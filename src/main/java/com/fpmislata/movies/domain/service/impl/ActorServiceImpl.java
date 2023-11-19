package com.fpmislata.movies.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.persistence.ActorRepository;
import com.fpmislata.movies.domain.service.ActorService;
import com.fpmislata.movies.dto.ActorDTO;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.mapper.ActorMapper;

@Service
public class ActorServiceImpl implements ActorService {
    
    @Autowired
    ActorRepository actorRepository;

    @Override
    public int create(ActorDTO actorDTO) {
        Actor actor = ActorMapper.mapper.toActor(actorDTO);
        return actorRepository.insert(ActorMapper.mapper.toActorDTO(actor));
    }

    @Override
    public void update(ActorDTO actorDTO) {
        actorRepository.find(actorDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + actorDTO.getId()));
        Actor actor = ActorMapper.mapper.toActor(actorDTO);
        actorRepository.update(ActorMapper.mapper.toActorDTO(actor));
    }

    @Override
    public void delete(int id) {
        actorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + id));
        actorRepository.delete(id);
    }

    @Override
    public ActorDTO find(int id) {
        ActorDTO actorDTO = actorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + id));
        return actorDTO;
    }
}