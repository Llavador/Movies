package com.fpmislata.movies.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.persistence.DirectorRepository;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.dto.DirectorDTO;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.mapper.DirectorMapper;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public int create(DirectorDTO directorDTO) {
        Director director = DirectorMapper.mapper.toDirector(directorDTO);
        return directorRepository.insert(DirectorMapper.mapper.toDirectorDTO(director));
    }

    @Override
    public void update(DirectorDTO directorDTO) {
        directorRepository.find(directorDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + directorDTO.getId()));
        Director director = DirectorMapper.mapper.toDirector(directorDTO);
        directorRepository.update(DirectorMapper.mapper.toDirectorDTO(director));
    }

    @Override
    public void delete(int id) {
        directorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        directorRepository.delete(id);
    }

    @Override
    public DirectorDTO find(int id) {
        DirectorDTO directorDTO = directorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        return directorDTO;
    }
}