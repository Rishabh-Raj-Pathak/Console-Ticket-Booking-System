package com.ticketbooking.services;

import com.ticketbooking.models.Movie;

import java.util.Date;
import java.util.List;

/**
 * Interface for movie-related operations.
 */
public interface MovieService {
    Movie createMovie(String title, String description, int durationInMinutes,
            String language, Date releaseDate, String genre);

    Movie getMovie(String id);

    List<Movie> getAllMovies();

    List<Movie> searchMovies(String title, String language, String genre, Date releaseDate);

    boolean updateMovie(Movie movie);

    boolean deleteMovie(String id);
}