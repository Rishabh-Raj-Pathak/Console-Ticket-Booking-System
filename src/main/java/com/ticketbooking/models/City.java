package com.ticketbooking.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a city where cinemas are located.
 */
public class City {
    private String id;
    private String name;
    private String state;
    private String country;
    private List<Cinema> cinemas;

    // Constructor
    public City(String id, String name, String state, String country) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.country = country;
        this.cinemas = new ArrayList<>();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Methods
    public boolean addCinema(Cinema cinema) {
        return cinemas.add(cinema);
    }

    public List<Cinema> getCinemas() {
        return new ArrayList<>(cinemas);
    }

    @Override
    public String toString() {
        return name + ", " + state + ", " + country;
    }
}