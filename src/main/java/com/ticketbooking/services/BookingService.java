package com.ticketbooking.services;

import com.ticketbooking.models.Booking;
import com.ticketbooking.models.Customer;
import com.ticketbooking.models.Payment;
import com.ticketbooking.models.Show;
import com.ticketbooking.models.enums.PaymentMethod;

import java.util.List;

/**
 * Interface for booking-related operations.
 */
public interface BookingService {
    Booking createBooking(Customer customer, Show show, List<String> seatIds);

    Booking getBooking(String id);

    List<Booking> getBookingsForCustomer(String customerId);

    List<Booking> getBookingsForShow(String showId);

    boolean confirmBooking(String bookingId, PaymentMethod paymentMethod);

    boolean cancelBooking(String bookingId);

    Payment getPayment(String id);
}