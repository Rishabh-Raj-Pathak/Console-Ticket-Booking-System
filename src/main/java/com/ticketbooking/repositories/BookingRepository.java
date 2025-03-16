package com.ticketbooking.repositories;

import com.ticketbooking.models.Booking;
import com.ticketbooking.models.Payment;

import java.util.List;

/**
 * Repository interface for Booking-related data operations.
 */
public interface BookingRepository {
    boolean saveBooking(Booking booking);
    Booking getBooking(String id);
    List<Booking> getBookingsForCustomer(String customerId);
    List<Booking> getBookingsForShow(String showId);
    boolean updateBooking(Booking booking);
    
    boolean savePayment(Payment payment);
    Payment getPayment(String id);
} 

   