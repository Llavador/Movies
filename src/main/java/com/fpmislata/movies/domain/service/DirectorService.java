package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.dto.DirectorDTO;

public interface DirectorService {

    int create(DirectorDTO directorDTO);
    
    void update(DirectorDTO directorDTO);
    
    void delete(int id);

    DirectorDTO find(int id);
}
