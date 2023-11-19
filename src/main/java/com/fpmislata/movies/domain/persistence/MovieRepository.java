package com.fpmislata.movies.domain.persistence;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.dto.MovieDTO;

public interface MovieRepository {

    List<MovieDTO> getAll(Integer page, Integer pageSize);

    Optional<MovieDTO> find(int id);
    
    int getTotalNumberOfRecords();

    int insert(MovieDTO movieDTO);

    void update(MovieDTO movieDTO);

    void delete(int id);
}
