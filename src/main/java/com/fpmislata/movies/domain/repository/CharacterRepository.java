package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.CharacterMovie;

public interface CharacterRepository {
    
    CharacterMovie find(int id);
}
