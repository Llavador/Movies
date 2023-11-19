package com.fpmislata.movies.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.persistence.ActorRepository;
import com.fpmislata.movies.domain.persistence.DirectorRepository;
import com.fpmislata.movies.domain.persistence.MovieRepository;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.dto.ActorDTO;
import com.fpmislata.movies.dto.DirectorDTO;
import com.fpmislata.movies.dto.MovieDTO;
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
    public List<MovieDTO> getAll(Integer page, Integer pageSize) {
        return movieRepository.getAll(page, pageSize);
    }

    @Override
    public List<MovieDTO> getAll() {
        return movieRepository.getAll(null, null);
    }

    @Override
    public MovieDTO find(int id) {
        MovieDTO movieDTO = movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Película no encontrada con id: " + id));

        DirectorDTO directorDTO = directorRepository.findByMovieId(id).orElse(null);
        movieDTO.setDirectorDTO(directorDTO);

        List<ActorDTO> actorDTOs = actorRepository.findByMovieId(id);

        movieDTO.setActorDTOs(actorDTOs);

        return movieDTO;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public int create(MovieDTO movieDTO, int directorId, List<Integer> actorIds) {
        DirectorDTO directorDTO = directorRepository.find(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + directorId));
        List<ActorDTO> actorDTOs = actorIds.stream()
                .map(actorId -> actorRepository.find(actorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + actorId))                )
                .toList();
        Movie movie = MovieMapper.mapper.toMovie(movieDTO);
        movie.setDirector(DirectorMapper.mapper.toDirector(directorDTO));
        movie.setActors(
                actorDTOs.stream()
                        .map(ActorMapper.mapper::toActor)
                        .toList()
        );
        return movieRepository.insert(MovieMapper.mapper.toMovieDTO(movie));
    }

    @Override
    public void update(MovieDTO movieDTO, int directorId, List<Integer> actorIds) {
        movieRepository.find(movieDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Película no encontrada con id: " + movieDTO.getId()));
        DirectorDTO directorDTO = directorRepository.find(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + directorId));
        List<ActorDTO> actorDTOs = actorIds.stream()
                .map(actorId -> actorRepository.find(actorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + actorId))                )
                .toList();
        Movie movie = MovieMapper.mapper.toMovie(movieDTO);
        movie.setDirector(DirectorMapper.mapper.toDirector(directorDTO));
        movie.setActors(
                actorDTOs.stream()
                        .map(ActorMapper.mapper::toActor)
                        .toList()
        );
        movieRepository.update(MovieMapper.mapper.toMovieDTO(movie));
    }

    @Override
    public void delete(int id) {
        movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        movieRepository.delete(id);
    }
}
