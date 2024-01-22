package com.fpmislata.movies.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.CharacterMovie;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.repository.ActorRepository;
import com.fpmislata.movies.domain.repository.DirectorRepository;
import com.fpmislata.movies.domain.repository.MovieRepository;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.mapper.DirectorMapper;
import com.fpmislata.movies.mapper.MovieMapper;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

        @Autowired
        private MovieRepository movieRepository;
        @Autowired
        private DirectorRepository directorRepository;
        @Autowired
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
                /*
                 * Movie movie = movieRepository.find(id)
                 * .orElseThrow(() -> new
                 * ResourceNotFoundException("Película no encontrada con id: " + id));
                 * 
                 * Director director = directorRepository.findByMovieId(id).orElse(null);
                 * movie.setDirector(director);
                 * 
                 * List<Actor> actors = actorRepository.findByMovieId(id);
                 * 
                 * movie.setActors(actors);
                 * 
                 * return movie;
                 */
                return movieRepository.find(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Película no encontrada con id: " + id));
        }

        @Override
        public long getTotalNumberOfRecords() {
                return movieRepository.getTotalNumberOfRecords();
        }

        @Override
        public Movie create(Movie movie) {
                movie.setDirector(directorRepository.find(movie.getDirector().getId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Director no encontrado con id: " + movie.getDirector().getId())));
                movie.getCharacterMovies().forEach(characterMovie -> characterMovie.setActor(actorRepository
                                .find(characterMovie.getActor().getId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Actor no encontrado con id: " + characterMovie.getActor().getId()))));
                return movieRepository.insert(movie);
        }

        @Override
        public Movie update(Movie movie) {
                Movie existingMovie = movieRepository.find(movie.getId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Película no encontrado con id: " + movie.getId()));
                MovieMapper.mapper.updateMovieFromMovieUpdate(movie, existingMovie);
                return movieRepository.update(existingMovie);
        }

        @Override
        public void delete(int id) {
                Movie movie = movieRepository.find(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Película no encontrada con id: " + id));
                movieRepository.delete(movie);
        }

        @Override
        public List<Movie> findByDirectorId(int directorId) {
                return movieRepository.findByDirectorId(directorId);
        }

        @Override
        public Movie addCharacterMovie(int id, CharacterMovie characterMovie) {
                Movie movie = this.find(id);
                Actor actor = actorRepository.find(characterMovie.getActor().getId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Actor no encontrado con id: " + characterMovie.getActor().getId()));
                characterMovie.setActor(actor);
                movie.addCharacterMovie(characterMovie); // por si hay alguna validación
                return movieRepository.update(movie);
        }

        @Override
        public Movie updateCharacterMovie(int id, CharacterMovie characterMovie) {
                Movie movie = this.find(id);
                Actor actor = actorRepository.find(characterMovie.getActor().getId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Actor no encontrado con id: " + characterMovie.getActor().getId()));
                characterMovie.setActor(actor);
                movie.updateCharacterMovie(characterMovie); // por si hay alguna validación
                return movieRepository.update(movie);
        }
}
