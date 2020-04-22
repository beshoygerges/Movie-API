package com.movie.api.controller;

import com.movie.api.exception.MovieAPIException.MovieNotFoundException;
import com.movie.api.model.Movie;
import com.movie.api.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final IMovieService movieService;

    @Autowired
    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public Iterable<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable @NotNull Integer id) throws MovieNotFoundException {
        return movieService.getMovieById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addMovie(@RequestBody @Valid Movie movie) {
        movieService.addMovie(movie);
    }

    @PutMapping
    public void updateMovie(@RequestBody @Valid Movie movie) throws MovieNotFoundException {
        movieService.updateMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable @NotNull Integer id) throws MovieNotFoundException {
        movieService.deleteMovieById(id);
    }
}
