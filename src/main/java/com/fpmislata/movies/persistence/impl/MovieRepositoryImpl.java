package com.fpmislata.movies.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.repository.MovieRepository;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.dao.CharacterMovieDAO;
import com.fpmislata.movies.persistence.dao.MovieDAO;
import com.fpmislata.movies.persistence.model.MovieEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieDAO movieDAO;

    @Autowired
    CharacterMovieDAO characterMovieDAO;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        List<MovieEntity> movieEntities;
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            movieEntities = movieDAO.findAll(pageable).stream().toList();
        } else {
            movieEntities = movieDAO.findAll();
        }
        return MovieMapper.mapper.toMovieList(movieEntities);
    }
    
    @Override
    public Optional<Movie> find(int id) {

        MovieEntity movieEntity = movieDAO.findById(id).orElse(null);
        if(movieEntity == null) {
            return Optional.empty();
        }
        return Optional.of(MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieEntity));
    }

    @Override
    public long getTotalNumberOfRecords() {
        return movieDAO.count();
    }

    @Override
    @Transactional
    public Movie insert(Movie movie) {
        MovieEntity movieEntity = movieDAO.save(MovieMapper.mapper.toMovieEntity(movie));
        return MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieEntity);
    }

    @Override
    @Transactional
    public Movie update(Movie movie) {
        MovieEntity movieEntity = movieDAO.save(MovieMapper.mapper.toMovieEntity(movie));
        return MovieMapper.mapper.toMovieWithDirectorAndCharacterMovies(movieEntity);
    }

    @Override
    public List<Movie> findByDirectorId(int directorId) {
        return MovieMapper.mapper.toMovieList(movieDAO.findByDirectorEntityId(directorId));
    }

    @Override
    @Transactional
    public void delete(Movie movie) {
        movieDAO.delete(MovieMapper.mapper.toMovieEntity(movie));
    }
/*
    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<MovieEntity> movieEntities = movieDAO.findAll(connection, page, pageSize);
            List<Movie> movies = movieEntities.stream()
                    .map(MovieMapper.mapper::toMovie)
                    .toList();
            return movies;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<MovieEntity> movieEntity = movieDAO.find(connection, id);
            if(movieEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        try(Connection connection = DBUtil.open(true)) {
            return movieDAO.getTotalNumberOfRecords(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Movie movie) {
        try (Connection connection = DBUtil.open(false)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            int id = movieDAO.insert(connection, movieEntity);
            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Movie movie) {
        try(Connection connection= DBUtil.open(true)) {
            movieDAO.update(connection, MovieMapper.mapper.toMovieEntity(movie));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection= DBUtil.open(true)) {
            movieDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/
}