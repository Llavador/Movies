package com.fpmislata.movies.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpmislata.movies.persistence.model.CharacterMovieEntity;

@Repository
public interface CharacterMovieDAO extends JpaRepository<CharacterMovieEntity, Integer> {

}