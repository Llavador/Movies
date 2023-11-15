package com.fpmislata.movies.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.persistence.DirectorRepository;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public int create(Director director) {
        return directorRepository.insert(director);
    }

    @Override
    public void update(int id, Director director) {
        Director existingDirector = directorRepository.find(director.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + director.getId()));
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        Optional<Director> director = directorRepository.find(id);
        if (director == null) {
            throw new ResourceNotFoundException("Director not found with id: " + id);
        }
        directorRepository.delete(id);
    }
}
