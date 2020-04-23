package com.movie.api.controller;

import com.movie.api.exception.MovieAPIException.MovieNotFoundException;
import com.movie.api.model.Movie;
import com.movie.api.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Movie> getMovieById(@PathVariable @NotNull Integer id) {
        try {
            Movie movie = movieService.getMovieById(id);
            return ResponseEntity.ok(movie);
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> addMovie(@RequestBody @Valid Movie movie) {
        movie = movieService.addMovie(movie);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody @Valid Movie movie) {
        try {
            movie = movieService.updateMovie(id, movie);
            return ResponseEntity.ok(movie);
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovieById(@PathVariable @NotNull Integer id) {
        try {
            movieService.deleteMovieById(id);
            return ResponseEntity.noContent().build();
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
