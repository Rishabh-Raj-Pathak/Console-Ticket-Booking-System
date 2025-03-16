package com.ticketbooking.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cinema (theater) in the system.
 */
public class Cinema {
    private String id;
    private String name;
    private Address address;
    private int totalCinemaHalls;
    private List<CinemaHall> halls;

    // Constructor
    public Cinema(String id, String name, Address address, int totalCinemaHalls) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.totalCinemaHalls = totalCinemaHalls;
        this.halls = new ArrayList<>();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getTotalCinemaHalls() {
        return totalCinemaHalls;
    }

    // Methods
    public boolean addHall(CinemaHall hall) {
        if (halls.size() < totalCinemaHalls) {
            return halls.add(hall);
        }
        return false;
    }

    public List<CinemaHall> getHalls() {
        return new ArrayList<>(halls);
    }

    @Override
    public String toString() {
        return name + " - " + address.getCity();
    }
}