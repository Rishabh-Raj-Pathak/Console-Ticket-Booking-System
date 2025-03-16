package com.ticketbooking.models;

import com.ticketbooking.models.enums.PaymentMethod;
import com.ticketbooking.models.enums.PaymentStatus;

import java.util.Date;

/**
 * Represents a payment for a booking.
 */
public class Payment {
    private String id;
    private double amount;
    private Date createdOn;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private Booking booking;

    // Constructor
    public Payment(String id, double amount, PaymentMethod paymentMethod, Booking booking) {
        this.id = id;
        this.amount = amount;
        this.createdOn = new Date();
        this.status = PaymentStatus.PENDING;
        this.paymentMethod = paymentMethod;
        this.booking = booking;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    // Methods
    public boolean processPayment() {
        // In a real system, this would integrate with a payment gateway
        // For our learning project, we'll simulate a successful payment
        this.status = PaymentStatus.COMPLETED;
        return true;
    }

    public boolean refund() {
        if (status == PaymentStatus.COMPLETED) {
            this.status = PaymentStatus.REFUNDED;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Payment #" + id + " - $" + amount + " (" + status + ")";
    }
}