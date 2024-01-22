package com.fpmislata.movies.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.repository.ActorRepository;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.persistence.dao.ActorDAO;
import com.fpmislata.movies.persistence.model.ActorEntity;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Autowired
    ActorDAO actorDAO;

    @Override
    public int insert(Actor actor) {
        return 0;
    }

    @Override
    public Optional<Actor> find(int id) {
        ActorEntity actorEntity = actorDAO.findById(id).orElse(null);
        if(actorEntity == null) {
            return Optional.empty();
        }
        return Optional.of(ActorMapper.mapper.toActor(actorEntity));
    }

    @Override
    public List<Actor> findByMovieId(int movieId) {
        return null;
    }

    @Override
    public void update(Actor actor) {

    }

    @Override
    public void delete(int id) {

    }
/*    
    @Override
    public int insert(Actor actor) {
        try (Connection connection = DBUtil.open(true)){
            return actorDAO.insert(connection, ActorMapper.mapper.toActorEntity(actor));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Actor> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<ActorEntity> actorEntity = actorDAO.find(connection, id);
            if(actorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(ActorMapper.mapper.toActor(actorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Actor> findByMovieId(int movieId) {
        try (Connection connection= DBUtil.open(true)){
            List<ActorEntity> actorEntities = actorDAO.findByMovieId(connection, movieId);
            List<Actor> actors = actorEntities.stream()
                    .map(ActorMapper.mapper::toActor)
                    .toList();
            return actors;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Actor actor) {
        try(Connection connection= DBUtil.open(true)) {
            actorDAO.update(connection, ActorMapper.mapper.toActorEntity(actor));
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
*/
}