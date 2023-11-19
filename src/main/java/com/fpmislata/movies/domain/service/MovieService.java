package com.fpmislata.movies.domain.service;

import java.util.List;

import com.fpmislata.movies.dto.MovieDTO;

public interface MovieService {

    List<MovieDTO> getAll(Integer page, Integer pageSize);
    
    List<MovieDTO> getAll();

    MovieDTO find(int id);

    int getTotalNumberOfRecords();
    
    int create(MovieDTO movieDTO, int directorId, List<Integer> actorIds);
    
}
