package com.fpmislata.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.service.ActorService;

@RequestMapping("/actors")
@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    List<Actor> actors = List.of(
        new Actor("Robert Downey Jr.", 1968, null),
        new Actor("Chris Evans", 1981, null));
 
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/insert")
    public void create(){
        try {
            for (Actor actor: this.actors) {
                actorService.create(actor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
