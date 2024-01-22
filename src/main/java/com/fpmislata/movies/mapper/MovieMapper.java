package com.fpmislata.movies.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.fpmislata.movies.controller.model.character.CharacterMovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.controller.model.movie.MovieUpdateWeb;
import com.fpmislata.movies.domain.entity.CharacterMovie;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.persistence.model.MovieEntity;

@Mapper(componentModel = "spring")
public interface MovieMapper {
/*
    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    MovieListWeb toMovieListWeb(Movie movie);
    
    List<MovieListWeb> toMovieListWebs(List<Movie> movies);

    @Mapping(target = "characterMovies", expression = "java(CharacterMovieMapper.mapper.toCharacterMovieListWeb(movie.getCharacterMovies()))")
    @Mapping(target = "director", ignore = true)
    MovieDetailWeb toMovieDetailWeb(Movie movie);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    @Mapping(target = "actorEntities", ignore = true)
    @Mapping(target = "directorEntity", ignore = true)
    @Mapping(target = "characterMovieEntities", ignore = true)
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;

    //@Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieEntity.getDirectorEntity()))")
    //@Mapping(target = "characterMovies", expression = "java(CharacterMovieMapper.mapper.toCharacterMovies(movieEntity.getCharacterMovieEntities()))")
    @Mapping(target = "characterMovies", ignore = true)
    @Mapping(target = "director", ignore = true)
    Movie toMovie(MovieEntity movieEntity);

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "characterMovies", ignore = true)
    @IterableMapping(qualifiedByName = "toMovie")
    @Named("toMovieList")
    List<Movie> toMovieList(List<MovieEntity> movieEntities);

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "characterMovies", ignore = true)
    @Mapping(target = "id", ignore = true)
    Movie toMovie(MovieCreateWeb movieCreateWeb);

    //@Mapping(target = "directorId", expression = "java(movie.getDirector().getId())")
    //@Mapping(target = "actorIds", expression = "java(mapActorsToActorIds(movie.getActors()))")
    @Mapping(target = "directorEntity", expression = "java(movie.getDirector().getId())")
    @Mapping(target = "actorEntities", expression = "java(mapActorsToActorIds(movie.getActors()))")
    MovieEntity toMovieEntity(Movie movie);

    @Named("actorToActorIds")
    default List<Integer> mapActorsToActorIds(List<Actor> actors) {
        return actors.stream()
                .map(actor -> actor.getId())
                .toList();
    }
    
    @Mapping(target = "director", ignore = true)
    @Mapping(target = "actors", ignore = true)
    Movie toMovie(MovieUpdateWeb movieUpdateWeb);
*/

    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    MovieListWeb toMovieListWeb(Movie movie);

    List<MovieListWeb> toMovieListWebs(List<Movie> movies);

    @Mapping(target = "characterMovies", expression = "java(CharacterMovieMapper.mapper.toCharacterMovieListWeb(movie.getCharacterMovies()))")
    MovieDetailWeb toMovieDetailWeb(Movie movie);

    /*@Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characterMovies", expression = "java(CharacterMovieMapper.mapper.toCharacterMovies(movieEntity.getCharacterMovieEntities()))")*/
    @Mapping(target = "director", ignore = true)
    @Mapping(target = "characterMovies", ignore = true)
    @Named("toMovie")
    Movie toMovie(MovieEntity movieEntity);

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "characterMovies", ignore = true)
    @IterableMapping(qualifiedByName = "toMovie")
    @Named("toMovieList")
    List<Movie> toMovieList(List<MovieEntity> movieEntities);

    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characterMovies", expression = "java(CharacterMovieMapper.mapper.toCharacterMovies(movieEntity.getCharacterMovieEntities()))")
    @Named("toMovieWithDirectorAndCharacterMovies")
    Movie toMovieWithDirectorAndCharacterMovies(MovieEntity movieEntity);

    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieCreateWeb.getDirectorId()))")
    @Mapping(target = "characterMovies", expression = "java(mapCharactersMoviesCreateWebsToCharacterMovies(movieCreateWeb.getCharacters()))")
    Movie toMovie(MovieCreateWeb movieCreateWeb);

    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieUpdateWeb.getDirectorId()))")
    Movie toMovie(MovieUpdateWeb movieUpdateWeb);

    @Named("mapCharactersMoviesCreateWebsToCharacterMovies")
    default List<CharacterMovie> mapCharactersMoviesCreateWebsToCharacterMovies(List<CharacterMovieCreateWeb> characterMovieCreateWebs) {
        return characterMovieCreateWebs.stream()
                .map(characterMovieCreateWeb -> CharacterMovieMapper.mapper.toCharacterMovie(
                        characterMovieCreateWeb.getActorId(),
                        characterMovieCreateWeb.getCharacters()))
                .toList();
    }

    @Mapping(target = "directorEntity", expression = "java(DirectorMapper.mapper.toDirectorEntity(movie.getDirector()))")
    @Mapping(target = "characterMovieEntities", expression = "java(CharacterMovieMapper.mapper.toCharacterMovieEntities(movie.getCharacterMovies()))")
    MovieEntity toMovieEntity(Movie movie);

    /*@Mapping(target = "directorEntity", expression = "java(DirectorMapper.mapper.toDirectorEntity(movie.getDirector()))")
    @Mapping(target = "characterMovieEntities", expression = "java(mapCharactersMoviesToCharacterMovieEntities(movie.getCharacterMovies()))")
    MovieEntity toMovieEntity(Movie movie);

    @Named("mapCharactersMoviesToCharacterMovieEntities")
    default List<CharacterMovieEntity> mapCharactersMoviesToCharacterMovieEntities(List<CharacterMovie> characterMovies) {
        return characterMovies.stream()
                .map(CharacterMovieMapper.mapper::toCharacterMovieEntity)
                .toList();
    }*/


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "characterMovies", ignore = true)
    void updateMovieFromMovieUpdate(Movie movie, @MappingTarget Movie existingMovie);
}
