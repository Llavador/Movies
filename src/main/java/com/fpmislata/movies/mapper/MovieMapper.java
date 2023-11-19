package com.fpmislata.movies.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.fpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.domain.entity.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {
 
    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);
 
    MovieListWeb toMovieListWeb(Movie movie);
    MovieDetailWeb toMovieDetailWeb(Movie movie);
}
