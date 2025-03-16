package com.ticketbooking.models;

/**
 * Represents a seat for a specific show.
 * This links a physical CinemaHallSeat to a specific Show.
 */
public class ShowSeat {
    private String id;
    private CinemaHallSeat cinemaHallSeat;
    private Show show;
    private boolean isReserved;
    private Booking booking;

    // Constructor
    public ShowSeat(String id, CinemaHallSeat cinemaHallSeat, Show show) {
        this.id = id;
        this.cinemaHallSeat = cinemaHallSeat;
        this.show = show;
        this.isReserved = false;
        this.booking = null;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public CinemaHallSeat getCinemaHallSeat() {
        return cinemaHallSeat;
    }

    public Show getShow() {
        return show;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public Booking getBooking() {
        return booking;
    }

    // Methods
    public boolean reserve(Booking booking) {
        if (!isReserved) {
            this.isReserved = true;
            this.booking = booking;
            return true;
        }
        return false;
    }

    public void unreserve() {
        this.isReserved = false;
        this.booking = null;
    }

    @Override
    public String toString() {
        return cinemaHallSeat.toString() + (isReserved ? " (Reserved)" : " (Available)");
    }
}