package com.fpmislata.movies.domain.persistence;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.dto.ActorDTO;

public interface ActorRepository {

    int insert(ActorDTO actorDTO);
    
    Optional<ActorDTO> find(int id);

    List<ActorDTO> findByMovieId(int movieId);

    void update(ActorDTO actorDTO);

    void delete(int id);
    
}
