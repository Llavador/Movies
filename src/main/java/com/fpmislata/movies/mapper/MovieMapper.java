package com.fpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.controller.model.movie.MovieUpdateWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.dto.ActorDTO;
import com.fpmislata.movies.dto.MovieDTO;
import com.fpmislata.movies.persistence.model.MovieEntity;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    MovieListWeb toMovieListWeb(MovieDTO movieDTO);

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "actors", ignore = true)
    MovieDetailWeb toMovieDetailWeb(MovieDTO movieDTO);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runTime", expression = "java(resultSet.getInt(\"runtime\"))")
    @Mapping(target = "actorIds", ignore = true)
    @Mapping(target = "directorId", ignore = true)
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;

    @Mapping(target = "actorDTOs", ignore = true)
    @Mapping(target = "directorDTO", ignore = true)
    MovieDTO toMovieDTO(MovieEntity movieEntity);
    
    @Mapping(target = "director", ignore = true)
    @Mapping(target = "actors", ignore = true)
    Movie toMovie(MovieDTO movieDTO);

    @Mapping(target = "directorDTO", expression = "java(DirectorMapper.mapper.toDirectorDTO(movie.getDirector()))")
    @Mapping(target = "actorDTOs", expression = "java(mapActorsToActorDTOs(movie.getActors()))")
    MovieDTO toMovieDTO(Movie movie);
    
    @Named("actorDTOsToActorListWebs")
    default List<ActorDTO> mapActorsToActorDTOs(List<Actor> actors) {
        return actors.stream()
                .map(ActorMapper.mapper::toActorDTO)
                .toList();
    }

    @Mapping(target = "directorDTO", ignore = true)
    @Mapping(target = "actorDTOs", ignore = true)
    @Mapping(target = "id", ignore = true)
    MovieDTO toMovieDTO(MovieCreateWeb movieCreateWeb);

    @Mapping(target = "directorId", expression = "java(movie.getDirector().getId())")
    @Mapping(target = "actorIds", expression = "java(mapActorsToActorIds(movie.getActors()))")
    MovieEntity toMovieEntity(MovieDTO movieDTO);

    @Named("actorToActorIds")
    default List<Integer> mapActorsToActorIds(List<ActorDTO> actorDTOs) {
        return actorDTOs.stream()
                .map(actorDTO -> actorDTO.getId())
                .toList();
    }
    
    @Mapping(target = "directorDTO", ignore = true)
    @Mapping(target = "actorDTOs", ignore = true)
    MovieDTO toMovieDTO(MovieUpdateWeb movieUpdateWeb);
}
