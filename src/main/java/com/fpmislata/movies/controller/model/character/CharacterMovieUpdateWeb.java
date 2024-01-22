package com.fpmislata.movies.controller.model.character;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterMovieUpdateWeb {

    int id;
    int actorId;
    String characters;
}