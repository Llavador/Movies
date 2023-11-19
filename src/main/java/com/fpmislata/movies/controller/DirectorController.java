package com.fpmislata.movies.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.fpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.dto.DirectorDTO;
import com.fpmislata.movies.http_response.Response;
import com.fpmislata.movies.mapper.DirectorMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/directors")
@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody DirectorCreateWeb directorCreateWeb){
        int id = directorService.create(DirectorMapper.mapper.toDirectorDTO(directorCreateWeb));
        DirectorDetailWeb directorDetailWeb = new DirectorDetailWeb(
                id,
                directorCreateWeb.getName(),
                directorCreateWeb.getBirthYear(),
                directorCreateWeb.getDeathYear()
        );
        Response response = Response.builder()
            .data(directorDetailWeb)
            .build();
        return response;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody DirectorUpdateWeb directorUpdateWeb) {
        directorUpdateWeb.setId(id);
        directorService.update(DirectorMapper.mapper.toDirectorDTO(directorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        DirectorDTO directorDTO = directorService.find(id);
        Response response = Response.builder()
            .data(DirectorMapper.mapper.toDirectorDetailWeb(directorDTO))
            .build();
        return response;
    }
}
