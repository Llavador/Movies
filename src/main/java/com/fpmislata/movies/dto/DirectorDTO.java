package com.fpmislata.movies.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectorDTO {

    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;

}