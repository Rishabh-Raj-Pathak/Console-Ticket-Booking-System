package com.ticketbooking.models.enums;

/**
 * Represents the possible statuses of a payment in the system.
 */
public enum PaymentStatus {
    UNPAID, // Payment has not been made
    PENDING, // Payment is being processed
    COMPLETED, // Payment has been successfully completed
    DECLINED, // Payment was declined
    REFUNDED // Payment was refunded to the customer
}