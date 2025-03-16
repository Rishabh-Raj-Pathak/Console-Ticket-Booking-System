package com.ticketbooking.models.enums;

/**
 * Represents the possible statuses of a booking in the system.
 */
public enum BookingStatus {
    PENDING, // Booking is created but not confirmed (e.g., payment pending)
    CONFIRMED, // Booking is confirmed and active
    CANCELED, // Booking has been canceled by the customer or admin
    COMPLETED // Show has passed, booking is completed
}