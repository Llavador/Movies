package com.fpmislata.movies.domain.service.impl;

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
    public void update(Director director) {
        Director existingDirector = directorRepository.find(director.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + director.getId()));
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        Director director = directorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        directorRepository.delete(id);
    }

    @Override
    public Director find(int id) {
        Director director = directorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        return director;
    }
}
