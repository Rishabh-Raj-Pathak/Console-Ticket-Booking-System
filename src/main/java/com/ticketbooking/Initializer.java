package com.ticketbooking;

import com.ticketbooking.repositories.*;
import com.ticketbooking.repositories.impl.*;
import com.ticketbooking.services.*;
import com.ticketbooking.services.impl.*;

/**
 * Responsible for initializing the system components.
 */
public class Initializer {

    /**
     * Initialize repositories and services.
     */
    public static void initializeSystem() {
        // Initialize repositories
        AppContext.userRepository = new InMemoryUserRepository();
        AppContext.movieRepository = new InMemoryMovieRepository();
        AppContext.cinemaRepository = new InMemoryCinemaRepository();
        AppContext.showRepository = new InMemoryShowRepository();
        AppContext.bookingRepository = new InMemoryBookingRepository();

        // Initialize services
        AppContext.userService = new UserServiceImpl(AppContext.userRepository);
        AppContext.movieService = new MovieServiceImpl(AppContext.movieRepository);
        AppContext.cinemaService = new CinemaServiceImpl(AppContext.cinemaRepository);
        AppContext.showService = new ShowServiceImpl(AppContext.showRepository, AppContext.cinemaRepository);
        AppContext.bookingService = new BookingServiceImpl(AppContext.bookingRepository, AppContext.showRepository);
    }
}