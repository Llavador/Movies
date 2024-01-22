package com.fpmislata.movies.domain.entity;

import java.util.List;
import java.util.Objects;

import com.fpmislata.movies.exception.ResourceNotFoundException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Movie {
 
    private int id;
    private String title;
    private int year;
    private int runtime;
    private Director director;
    private List<CharacterMovie> characterMovies;
 
    public Movie(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }
 
    public Movie(String title, int year, int runtime) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }

    public void addCharacterMovie(CharacterMovie characterMovie) {
        //posibles validaciones
        characterMovies.add(characterMovie);
    }

    public void updateCharacterMovie(CharacterMovie updatedCharacterMovie) {
        //posibles validaciones
        characterMovies.stream()
                .filter(characterMovie -> Objects.equals(characterMovie.getId(), updatedCharacterMovie.getId()))
                .findFirst()
                .ifPresentOrElse(characterMovie -> {
                    characterMovie.setActor(updatedCharacterMovie.getActor());
                    characterMovie.setCharacters(updatedCharacterMovie.getCharacters());
                }, () -> { throw new ResourceNotFoundException("Personaje no encontrado con id: " + updatedCharacterMovie.getId());
                        }
                );
    }
}