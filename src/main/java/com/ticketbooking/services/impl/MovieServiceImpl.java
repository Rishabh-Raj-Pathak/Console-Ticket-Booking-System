package com.ticketbooking.services.impl;

import com.ticketbooking.models.Movie;
import com.ticketbooking.repositories.MovieRepository;
import com.ticketbooking.services.MovieService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the MovieService interface.
 * This class provides operations for creating, retrieving, updating, and
 * deleting movies.
 */
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(String title, String description, int durationInMinutes, String language, Date releaseDate,
            String genre) {
        String id = UUID.randomUUID().toString();
        Movie movie = new Movie(id, title, description, durationInMinutes, language, releaseDate, genre);
        movieRepository.saveMovie(movie);
        return movie;
    }

    @Override
    public Movie getMovie(String id) {
        return movieRepository.getMovie(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    @Override
    public List<Movie> searchMovies(String title, String language, String genre, Date releaseDate) {
        return movieRepository.searchMovies(title, language, genre, releaseDate);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return movieRepository.updateMovie(movie);
    }

    @Override
    public boolean deleteMovie(String id) {
        return movieRepository.deleteMovie(id);
    }
}