package com.ticketbooking.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a movie in the system.
 */
public class Movie {
    private String id;
    private String title;
    private String description;
    private int durationInMinutes;
    private String language;
    private Date releaseDate;
    private String genre;
    private List<Show> shows;

    // Constructor
    public Movie(String id, String title, String description, int durationInMinutes,
            String language, Date releaseDate, String genre) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.language = language;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.shows = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Methods
    public boolean addShow(Show show) {
        return shows.add(show);
    }

    public List<Show> getShows() {
        return new ArrayList<>(shows);
    }

    @Override
    public String toString() {
        return title + " (" + language + ") - " + durationInMinutes + " mins";
    }
}