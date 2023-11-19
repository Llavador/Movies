package com.fpmislata.movies.domain.persistence;

import java.util.Optional;

import com.fpmislata.movies.dto.DirectorDTO;

public interface DirectorRepository {

    int insert(DirectorDTO directorDTO);
    
    void update(DirectorDTO directorDTO);

    Optional<DirectorDTO> find(int id);
    
    void delete(int id);

    Optional<DirectorDTO> findByMovieId(int movieId);

}
