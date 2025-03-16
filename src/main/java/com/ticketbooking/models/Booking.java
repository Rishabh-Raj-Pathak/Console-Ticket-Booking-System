package com.ticketbooking.models;

import com.ticketbooking.models.enums.BookingStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a booking made by a customer.
 */
public class Booking {
    private String id;
    private Customer customer;
    private Show show;
    private List<ShowSeat> seats;
    private int numberOfSeats;
    private Date createdOn;
    private BookingStatus status;
    private Payment payment;

    // Constructor
    public Booking(String id, Customer customer, Show show, List<ShowSeat> seats) {
        this.id = id;
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
        this.numberOfSeats = seats.size();
        this.createdOn = new Date();
        this.status = BookingStatus.PENDING;

        // Add this booking to the customer
        customer.makeBooking(this);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Show getShow() {
        return show;
    }

    public List<ShowSeat> getSeats() {
        return new ArrayList<>(seats);
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    // Methods
    public boolean makePayment(Payment payment) {
        this.payment = payment;
        if (payment.processPayment()) {
            this.status = BookingStatus.CONFIRMED;
            return true;
        }
        return false;
    }

    public boolean cancel() {
        if (status != BookingStatus.CONFIRMED) {
            return false;
        }

        // Check if cancellation is allowed (e.g., not too close to show time)
        Date now = new Date();
        long timeUntilShow = show.getStartTime().getTime() - now.getTime();
        long hoursUntilShow = timeUntilShow / (60 * 60 * 1000);

        if (hoursUntilShow < 2) {
            return false; // Cannot cancel less than 2 hours before show
        }

        // Process refund
        if (payment != null) {
            payment.refund();
        }

        // Unreserve seats
        for (ShowSeat seat : seats) {
            seat.unreserve();
        }

        this.status = BookingStatus.CANCELED;
        return true;
    }

    public double getTotalAmount() {
        double total = 0;
        for (ShowSeat seat : seats) {
            total += seat.getCinemaHallSeat().getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Booking #" + id + " - " + show.getMovie().getTitle() + " (" + numberOfSeats + " seats)";
    }
}