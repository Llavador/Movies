package com.fpmislata.movies.controller.model.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MovieUpdateWeb {

    private int id;
    private String title;
    private int year;
    private int runtime;
    private int directorId;
}
