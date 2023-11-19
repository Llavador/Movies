package com.fpmislata.movies.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.sql.Connection;
import org.springframework.stereotype.Component;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.model.MovieEntity;

@Component
public class MovieDAO {

    public List<MovieEntity> findAll(Connection connection, Integer page, Integer pageSize) {
        List<Object> params = null;
        String sql = "SELECT * FROM movies";
        if (page != null) {
            int offset = (page - 1) * pageSize;
            sql += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<MovieEntity> movieEntities = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                movieEntities.add(MovieMapper.mapper.toMovieEntity(resultSet));
            }
            return movieEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Optional<MovieEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next() ? MovieMapper.mapper.toMovieEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public int getTotalNumberOfRecords(Connection connection) {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("SQL: " + SQL);
        }
    }

    public void addActor(Connection connection, int movieId, int actorId) {
        final String SQL = "INSERT INTO actors_movies (actor_id, movie_id) VALUES (?, ?)";
        DBUtil.insert(connection, SQL, List.of(actorId, movieId));
    }

    public int insert(Connection connection, MovieEntity movieEntity) throws SQLException {
        try {
            final String SQL = "INSERT INTO movies (title, year, runtime, director_id) VALUES (?, ?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(movieEntity.getTitle());
            params.add(movieEntity.getYear());
            params.add(movieEntity.getRunTime());
            params.add(movieEntity.getDirectorId());
            int id = DBUtil.insert(connection, SQL, params);
            movieEntity.getActorIds().stream()
                    .forEach(actorId -> addActor(connection, id, actorId));
            connection.commit();
            return id;
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    public void update(Connection connection, MovieEntity movieEntity) throws SQLException {
        try {
            final String SQL = "UPDATE movies SET title = ?, year = ?, runtime = ?, directorId = ? WHERE id = ?";
            List<Object> params = new ArrayList<>();
            params.add(movieEntity.getTitle());
            params.add(movieEntity.getYear());
            params.add(movieEntity.getRunTime());
            params.add(movieEntity.getDirectorId());
            params.add(movieEntity.getId());
            DBUtil.update(connection, SQL, params);
            removeActorsFromMovie(connection, movieEntity.getId());
            movieEntity.getActorIds().stream()
                    .forEach(actorId -> addActor(connection, movieEntity.getId(), actorId));
            connection.commit();
            DBUtil.close(connection);
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM movies WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
        removeActorsFromMovie(connection, id);
        DBUtil.close(connection);
    }

    public void removeActorsFromMovie(Connection connection, int id) {
        final String SQL = "DELETE FROM actors_movies WHERE movie_id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
        DBUtil.close(connection);
    }

}