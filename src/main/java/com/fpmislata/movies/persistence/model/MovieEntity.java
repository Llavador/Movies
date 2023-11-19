package com.fpmislata.movies.persistence.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
 
    private int id;
    private String title;
    private int year;
    private int runTime;
    private int directorId;
    private List<Integer> actorIds;
 
 
    public MovieEntity(int id, String title, int year, int runTime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
    }
}