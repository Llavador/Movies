package com.fpmislata.movies.controller.model.movie;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fpmislata.movies.controller.model.character.CharacterMovieListWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailWeb {
 
    private int id;
    private String title;
    private int year;
    private int runtime;
    private DirectorListWeb director;
    @JsonProperty("characters")
    private List<CharacterMovieListWeb> characterMovies;
}