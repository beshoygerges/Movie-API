package com.movie.api.service;

import com.movie.api.exception.MovieAPIException;
import com.movie.api.model.Movie;

public interface IMovieService {
    Iterable<Movie> getAllMovies();

    Movie addMovie(Movie movie);

    void deleteMovieById(Integer id) throws MovieAPIException.MovieNotFoundException;

    Movie updateMovie(int id, Movie movie) throws MovieAPIException.MovieNotFoundException;

    Movie getMovieById(Integer id) throws MovieAPIException.MovieNotFoundException;
}
