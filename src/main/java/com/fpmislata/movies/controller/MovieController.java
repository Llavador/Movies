package com.fpmislata.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.controller.model.movie.MovieUpdateWeb;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.dto.MovieDTO;
import com.fpmislata.movies.http_response.Response;
import com.fpmislata.movies.mapper.MovieMapper;

import java.util.List;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    public static final String MOVIES = "/movies";

    @Value("${default.pageSize}")
    private int defaultPageSize;

    @Value("${application.url}")
    private String urlBase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null) ? pageSize : defaultPageSize;
        List<MovieDTO> movieDTOs = (page != null) ? movieService.getAll(page, pageSize) : movieService.getAll();
        List<MovieListWeb> moviesWeb = movieDTOs.stream()
                .map(movieDTO -> MovieMapper.mapper.toMovieListWeb(movieDTO))
                .toList();
        int totalRecords = movieService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(moviesWeb)
                .totalRecords(totalRecords)
                .build();
        if (page != null) {
            response.paginate(page, pageSize, urlBase);
        }
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        MovieDTO movieDTO = movieService.find(id);
        Response response = Response.builder()
                .data(MovieMapper.mapper.toMovieDetailWeb(movieDTO))
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieCreateWeb movieCreateWeb) {
        int id = movieService.create(
                MovieMapper.mapper.toMovieDTO(movieCreateWeb),
                movieCreateWeb.getDirectorId(),
                movieCreateWeb.getActorIds());
        MovieListWeb movieListWeb = new MovieListWeb();
        movieListWeb.setTitle(movieCreateWeb.getTitle());
        movieListWeb.setId(id);
        return Response.builder().data(movieListWeb).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody MovieUpdateWeb movieUpdateWeb) {
        movieUpdateWeb.setId(id);
        movieService.update(MovieMapper.mapper.toMovieDTO(movieUpdateWeb),
        movieUpdateWeb.getDirectorId(),
        movieUpdateWeb.getActorIds());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        movieService.delete(id);
    }
}