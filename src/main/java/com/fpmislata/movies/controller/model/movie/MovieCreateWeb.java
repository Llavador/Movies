package com.fpmislata.movies.controller.model.movie;

import java.util.List;

import com.fpmislata.movies.controller.model.character.CharacterMovieCreateWeb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieCreateWeb {
 
    private String title;
    private int year;
    private int runtime;
    private int directorId;
    private List<CharacterMovieCreateWeb> characters;
}
