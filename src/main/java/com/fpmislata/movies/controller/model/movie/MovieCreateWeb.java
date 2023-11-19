package com.fpmislata.movies.controller.model.movie;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieCreateWeb {
 
    private String title;
    private int year;
    private int runTime;
    private int directorId;
    private List<Integer> actorIds;
}
