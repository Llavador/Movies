package com.fpmislata.movies.domain.entity;

import lombok.Data;

@Data
public class CharacterMovie {

    private int id;
    private Actor actor;
    private String characters;
}