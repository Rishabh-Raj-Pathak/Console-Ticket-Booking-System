package com.ticketbooking;

import com.ticketbooking.models.User;
import java.util.Scanner;
import com.ticketbooking.repositories.*;
import com.ticketbooking.services.*;

public class AppContext {
    public static UserRepository userRepository;
    public static MovieRepository movieRepository;
    public static CinemaRepository cinemaRepository;
    public static ShowRepository showRepository;
    public static BookingRepository bookingRepository;

    public static UserService userService;
    public static MovieService movieService;
    public static CinemaService cinemaService;
    public static ShowService showService;
    public static BookingService bookingService;

    public static Scanner scanner = new Scanner(System.in);
    public static User currentUser;
}