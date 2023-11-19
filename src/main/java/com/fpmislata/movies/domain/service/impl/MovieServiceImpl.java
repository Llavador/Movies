package com.fpmislata.movies.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.persistence.ActorRepository;
import com.fpmislata.movies.domain.persistence.DirectorRepository;
import com.fpmislata.movies.domain.persistence.MovieRepository;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;
    private ActorRepository actorRepository;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        return movieRepository.getAll(page, pageSize);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll(null, null);
    }

    @Override
    public Movie find(int id) {
        Movie movie = movieRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrada con id: " + id));
        return movie;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public int create(Movie movie, int directorId, List<Integer> actorIds) {
        Director director = directorRepository.find(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));
        List<Actor> actors = actorIds.stream()
                .map(actorId -> actorRepository.find(actorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId)))
                .toList();
        movie.setDirector(director);
        movie.setActors(actors);
        return movieRepository.insert(movie);
    }
}
