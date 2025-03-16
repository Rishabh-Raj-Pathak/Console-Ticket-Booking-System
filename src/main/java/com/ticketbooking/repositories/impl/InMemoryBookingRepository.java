package com.ticketbooking.repositories.impl;

import com.ticketbooking.models.Booking;
import com.ticketbooking.models.Payment;
import com.ticketbooking.repositories.BookingRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of BookingRepository.
 */
public class InMemoryBookingRepository implements BookingRepository {
    private Map<String, Booking> bookings = new HashMap<>();
    private Map<String, Payment> payments = new HashMap<>();

    // Maps to track relationships
    private Map<String, String> bookingToCustomer = new HashMap<>(); // bookingId -> customerId
    private Map<String, String> bookingToShow = new HashMap<>(); // bookingId -> showId
    private Map<String, String> paymentToBooking = new HashMap<>(); // paymentId -> bookingId

    @Override
    public boolean saveBooking(Booking booking) {
        bookings.put(booking.getId(), booking);
        bookingToCustomer.put(booking.getId(), booking.getCustomer().getId());
        bookingToShow.put(booking.getId(), booking.getShow().getId());
        return true;
    }

    @Override
    public Booking getBooking(String id) {
        return bookings.get(id);
    }

    @Override
    public List<Booking> getBookingsForCustomer(String customerId) {
        return bookings.values().stream()
                .filter(booking -> bookingToCustomer.get(booking.getId()).equals(customerId))
                .collect(Collectors.toList());

    }

    @Override
    public List<Booking> getBookingsForShow(String showId) {
        return bookings.values().stream()
                .filter(booking -> bookingToShow.get(booking.getId()).equals(showId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateBooking(Booking booking) {
        if (bookings.containsKey(booking.getId())) {
            bookings.put(booking.getId(), booking);
            return true;
        }
        return false;
    }

    @Override
    public boolean savePayment(Payment payment) {
        payments.put(payment.getId(), payment);
        return true;
    }

    @Override
    public Payment getPayment(String id) {
        return payments.get(id);
    }
}