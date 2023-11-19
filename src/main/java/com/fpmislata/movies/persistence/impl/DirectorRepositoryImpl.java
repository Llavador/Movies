package com.fpmislata.movies.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.persistence.DirectorRepository;
import com.fpmislata.movies.dto.DirectorDTO;
import com.fpmislata.movies.mapper.DirectorMapper;
import com.fpmislata.movies.persistence.dao.DirectorDAO;
import com.fpmislata.movies.persistence.model.DirectorEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDAO;
 
    @Override
    public int insert(DirectorDTO directorDTO) {
        try (Connection connection = DBUtil.open(true)){
            return directorDAO.insert(connection, DirectorMapper.mapper.toDirectorEntity(directorDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<DirectorDTO> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<DirectorEntity> directorEntity = directorDAO.find(connection, id);
            if(directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirectorDTO(directorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(DirectorDTO directorDTO) {
        try(Connection connection= DBUtil.open(true)) {
            directorDAO.update(connection, DirectorMapper.mapper.toDirectorEntity(directorDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection= DBUtil.open(true)) {
            directorDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<DirectorDTO> findByMovieId(int movieId) {
        try(Connection connection= DBUtil.open(true)) {
            Optional<DirectorEntity> directorEntity = directorDAO.findByMovieId(connection, movieId);
            if(directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirectorDTO(directorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}