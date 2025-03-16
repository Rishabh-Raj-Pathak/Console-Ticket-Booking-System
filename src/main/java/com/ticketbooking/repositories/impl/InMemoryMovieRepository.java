package com.ticketbooking.repositories.impl;

import com.ticketbooking.models.Movie;
import com.ticketbooking.repositories.MovieRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of MovieRepository.
 */
public class InMemoryMovieRepository implements MovieRepository {
    private Map<String, Movie> movies = new HashMap<>();

    @Override
    public boolean saveMovie(Movie movie) {
        movies.put(movie.getId(), movie);
        return true;
    }

    @Override
    public Movie getMovie(String id) {
        return movies.get(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies.values());
    }

    @Override
    public List<Movie> searchMovies(String title, String language, String genre, Date releaseDate) {
        return movies.values().stream()
                .filter(movie -> (title == null || movie.getTitle().toLowerCase().contains(title.toLowerCase())))
                .filter(movie -> (language == null || movie.getLanguage().equalsIgnoreCase(language)))
                .filter(movie -> (genre == null || movie.getGenre().equalsIgnoreCase(genre)))
                .filter(movie -> (releaseDate == null || movie.getReleaseDate().equals(releaseDate)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateMovie(Movie movie) {
        if (movies.containsKey(movie.getId())) {
            movies.put(movie.getId(), movie);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMovie(String id) {
        return movies.remove(id) != null;
    }
}