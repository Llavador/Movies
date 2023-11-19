package com.fpmislata.movies.persistence.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.persistence.ActorRepository;
import com.fpmislata.movies.dto.ActorDTO;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.persistence.dao.ActorDAO;
import com.fpmislata.movies.persistence.model.ActorEntity;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Autowired
    ActorDAO actorDAO;
    
    @Override
    public int insert(ActorDTO actorDTO) {
        try (Connection connection = DBUtil.open(true)){
            return actorDAO.insert(connection, ActorMapper.mapper.toActorEntity(actorDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ActorDTO> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<ActorEntity> actorEntity = actorDAO.find(connection, id);
            if(actorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(ActorMapper.mapper.toActorDTO(actorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<ActorDTO> findByMovieId(int movieId) {
        try (Connection connection= DBUtil.open(true)){
            List<ActorEntity> actorEntities = actorDAO.findByMovieId(connection, movieId);
            List<ActorDTO> actorDTOs = actorEntities.stream()
                    .map(ActorMapper.mapper::toActorDTO)
                    .toList();
            return actorDTOs;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(ActorDTO actorDTO) {
        try(Connection connection= DBUtil.open(true)) {
            actorDAO.update(connection, ActorMapper.mapper.toActorEntity(actorDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection= DBUtil.open(true)) {
            actorDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}