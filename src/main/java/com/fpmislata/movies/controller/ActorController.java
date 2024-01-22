package com.fpmislata.movies.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.service.ActorService;
import com.fpmislata.movies.http_response.Response;
import com.fpmislata.movies.mapper.ActorMapper;

@RequestMapping("/actors")
@RestController
public class ActorController {

    @Autowired
    ActorService actorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody Actor actor){
        int id = actorService.create(actor);
        actor.setId(id);
        return Response.builder().data(actor).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Actor actor) {
        actor.setId(id);
        actorService.update(actor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        actorService.delete(id);
    }
    /*
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(ActorMapper.mapper.toActorDetailWeb(actorService.find(id))).build();
    }
     */
}
