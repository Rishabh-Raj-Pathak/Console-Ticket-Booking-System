package com.ticketbooking.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer who can book tickets.
 */
public class Customer extends User {
    private List<Booking> bookings;

    // Constructor
    public Customer(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
        this.bookings = new ArrayList<>();
    }

    // Methods
    public boolean makeBooking(Booking booking) {
        return bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public boolean cancelBooking(Booking booking) {
        return bookings.remove(booking);
    }
}