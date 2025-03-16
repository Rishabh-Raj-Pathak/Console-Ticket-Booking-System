package com.ticketbooking.services.impl;

import com.ticketbooking.models.*;
import com.ticketbooking.models.enums.BookingStatus;
import com.ticketbooking.models.enums.PaymentMethod;
import com.ticketbooking.repositories.BookingRepository;
import com.ticketbooking.repositories.ShowRepository;
import com.ticketbooking.services.BookingService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of BookingService interface.
 */
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, ShowRepository showRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
    }

    @Override
    public Booking createBooking(Customer customer, Show show, List<String> seatIds) {
        // Get the show seats
        List<ShowSeat> seats = new ArrayList<>();
        for (String seatId : seatIds) {
            ShowSeat seat = showRepository.getShowSeat(seatId);
            if (seat == null) {
                throw new IllegalArgumentException("Seat not found: " + seatId);
            }
            if (seat.isReserved()) {
                throw new IllegalArgumentException("Seat already reserved: " + seatId);
            }
            seats.add(seat);
        }

        // Create the booking
        String id = UUID.randomUUID().toString();
        Booking booking = new Booking(id, customer, show, seats);

        // Reserve the seats
        for (ShowSeat seat : seats) {
            seat.reserve(booking);
        }

        bookingRepository.saveBooking(booking);
        return booking;
    }

    @Override
    public Booking getBooking(String id) {
        return bookingRepository.getBooking(id);
    }

    @Override
    public List<Booking> getBookingsForCustomer(String customerId) {
        return bookingRepository.getBookingsForCustomer(customerId);
    }

    @Override
    public List<Booking> getBookingsForShow(String showId) {
        return bookingRepository.getBookingsForShow(showId);
    }

    @Override
    public boolean confirmBooking(String bookingId, PaymentMethod paymentMethod) {
        Booking booking = bookingRepository.getBooking(bookingId);
        if (booking == null) {
            return false;
        }

        // Create payment
        String paymentId = UUID.randomUUID().toString();
        double amount = booking.getTotalAmount();
        Payment payment = new Payment(paymentId, amount, paymentMethod, booking);

        // Process payment and update booking
        if (booking.makePayment(payment)) {
            bookingRepository.savePayment(payment);
            bookingRepository.updateBooking(booking);
            return true;
        }

        return false;
    }

    @Override
    public boolean cancelBooking(String bookingId) {
        Booking booking = bookingRepository.getBooking(bookingId);
        if (booking == null) {
            return false;
        }

        if (booking.cancel()) {
            bookingRepository.updateBooking(booking);
            return true;
        }

        return false;
    }

    @Override
    public Payment getPayment(String id) {
        return bookingRepository.getPayment(id);
    }
}