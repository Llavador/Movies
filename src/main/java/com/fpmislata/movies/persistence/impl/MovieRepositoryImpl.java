package com.fpmislata.movies.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.persistence.MovieRepository;
import com.fpmislata.movies.dto.MovieDTO;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.dao.MovieDAO;
import com.fpmislata.movies.persistence.model.MovieEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieDAO movieDAO;

    @Override
    public List<MovieDTO> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<MovieEntity> movieEntities = movieDAO.findAll(connection, page, pageSize);
            List<MovieDTO> movieDTOs = movieEntities.stream()
                    .map(MovieMapper.mapper::toMovieDTO)
                    .toList();
            return movieDTOs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<MovieDTO> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<MovieEntity> movieEntity = movieDAO.find(connection, id);
            if(movieEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(MovieMapper.mapper.toMovieDTO(movieEntity.get()));
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
    public int insert(MovieDTO movieDTO) {
        try (Connection connection = DBUtil.open(false)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movieDTO);
            int id = movieDAO.insert(connection, movieEntity);
            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}