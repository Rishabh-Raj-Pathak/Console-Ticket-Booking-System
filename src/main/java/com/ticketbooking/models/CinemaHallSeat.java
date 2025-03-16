package com.ticketbooking.models;

import com.ticketbooking.models.enums.SeatType;

/**
 * Represents a physical seat in a cinema hall.
 * 
 */
public class CinemaHallSeat {
    private String id;
    private int row;
    private int seatNumber;
    private SeatType seatType;
    private double price;

    // Constructor
    public CinemaHallSeat(String id, int row, int seatNumber, SeatType seatType, double price) {
        this.id = id;
        this.row = row;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Row " + row + ", Seat " + seatNumber + " (" + seatType + ")";
    }
}