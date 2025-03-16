package com.ticketbooking.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a movie show at a specific time in a specific cinema hall.
 */
public class Show {
    private String id;
    private Date createdOn;
    private Date startTime;
    private Date endTime;
    private Movie movie;
    private CinemaHall cinemaHall;
    private List<ShowSeat> seats;

    // Constructor
    public Show(String id, Date startTime, Date endTime, Movie movie, CinemaHall cinemaHall) {
        this.id = id;
        this.createdOn = new Date();
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.seats = new ArrayList<>();

        // Add this show to the movie and cinema hall
        movie.addShow(this);
        cinemaHall.addShow(this);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    // Methods
    public boolean addShowSeat(ShowSeat seat) {
        return seats.add(seat);
    }

    public List<ShowSeat> getSeats() {
        return new ArrayList<>(seats);
    }

    @Override
    public String toString() {
        return movie.getTitle() + " at " + cinemaHall.getName() + " - " + startTime;
    }
}