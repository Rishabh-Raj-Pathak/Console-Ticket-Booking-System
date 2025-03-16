package com.ticketbooking.repositories;

import com.ticketbooking.models.Movie;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for Movie-related data operations.
 */
public interface MovieRepository {
    boolean saveMovie(Movie movie);
    Movie getMovie(String id);
    List<Movie> getAllMovies();
    List<Movie> searchMovies(String title, String language, String genre, Date releaseDate);
    boolean updateMovie(Movie movie);
    boolean deleteMovie(String id);
} 
