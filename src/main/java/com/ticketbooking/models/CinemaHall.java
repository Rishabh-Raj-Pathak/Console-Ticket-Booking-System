package com.ticketbooking.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hall within a cinema.
 */
public class CinemaHall {
    private String id;
    private String name;
    private int totalSeats;
    private List<CinemaHallSeat> seats;
    private List<Show> shows;

    // Constructor
    public CinemaHall(String id, String name, int totalSeats) {
        this.id = id;
        this.name = name;
        this.totalSeats = totalSeats;
        this.seats = new ArrayList<>();
        this.shows = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    // Methods
    public boolean addSeat(CinemaHallSeat seat) {
        if (seats.size() < totalSeats) {
            return seats.add(seat);
        }
        return false;
    }

    public List<CinemaHallSeat> getSeats() {
        return new ArrayList<>(seats);
    }

    public boolean addShow(Show show) {
        return shows.add(show);
    }

    public List<Show> getShows() {
        return new ArrayList<>(shows);
    }

    @Override
    public String toString() {
        return name + " (" + totalSeats + " seats)";
    }
}