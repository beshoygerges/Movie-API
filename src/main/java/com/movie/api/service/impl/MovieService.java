package com.movie.api.service.impl;

import com.movie.api.model.Movie;
import com.movie.api.repository.MovieRepository;
import com.movie.api.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.movie.api.exception.MovieAPIException.MovieNotFoundException;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovieById(Integer id) throws MovieNotFoundException {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
    }

    @Override
    public Movie updateMovie(int id, Movie movie) throws MovieNotFoundException {
        getMovieById(id);
        movie.setId(id);
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(Integer id) throws MovieNotFoundException {
        Optional<Movie> optionalMovie;
        optionalMovie = movieRepository.findById(id);
        if (!optionalMovie.isPresent())
            throw new MovieNotFoundException("Movie with id " + id + " isn't found");
        return optionalMovie.get();
    }
}
