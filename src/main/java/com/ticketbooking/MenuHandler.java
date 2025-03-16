package com.ticketbooking;

import com.ticketbooking.models.*;
import com.ticketbooking.models.enums.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for displaying menus and handling user interactions.
 */
public class MenuHandler {

    /**
     * Displays the main menu and allows users to choose to log in or exit.
     */
    public void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (0-2): ");

            int choice = getIntInput();

            if (choice == 0) {
                break;
            } else if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Prompts the user for email and password, then logs in if valid.
     */
    private void login() {
        System.out.print("Enter your email: ");
        String email = AppContext.scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = AppContext.scanner.nextLine();

        AppContext.currentUser = AppContext.userService.login(email, password);
        if (AppContext.currentUser != null) {
            showUserMenu();
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    /**
     * Registers a new customer user.
     */
    private void register() {
        System.out.print("Enter your name: ");
        String name = AppContext.scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = AppContext.scanner.nextLine();
        System.out.print("Enter your phone: ");
        String phone = AppContext.scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = AppContext.scanner.nextLine();

        try {
            Customer customer = AppContext.userService.createCustomer(name, email, phone, password);
            System.out.println("Registration successful! Please login.");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    /**
     * Displays the user menu and allows the user to view movies or book tickets.
     */
    private void showUserMenu() {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Movies");
            System.out.println("2. Book Tickets");
            System.out.println("3. View My Bookings");
            System.out.println("0. Logout");
            System.out.print("Enter your choice (0-3): ");

            int choice = getIntInput();

            if (choice == 0) {
                AppContext.currentUser = null;
                break; // Logout to main menu
            } else if (choice == 1) {
                viewMovies();
            } else if (choice == 2) {
                bookTickets();
            } else if (choice == 3) {
                viewMyBookings();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays a list of available movies.
     */
    private void viewMovies() {
        List<Movie> movies = AppContext.movieService.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

        System.out.println("\nAvailable Movies:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }

        System.out.println("\nPress Enter to continue...");
        AppContext.scanner.nextLine();
    }

    /**
     * Allows the user to book tickets for a movie.
     */
    private void bookTickets() {
        // Get all available movies
        List<Movie> movies = AppContext.movieService.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies available for booking.");
            return;
        }

        // Display movies and let user select one
        System.out.println("\nSelect a movie:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }
        System.out.print("Enter your choice (1-" + movies.size() + "): ");
        int movieChoice = getIntInput();

        if (movieChoice < 1 || movieChoice > movies.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Movie selectedMovie = movies.get(movieChoice - 1);

        // Get shows for the selected movie
        List<Show> shows = AppContext.showService.getShowsForMovie(selectedMovie.getId());
        if (shows.isEmpty()) {
            System.out.println("No shows available for this movie.");
            return;
        }

        // Display shows and let user select one
        System.out.println("\nSelect a show:");
        for (int i = 0; i < shows.size(); i++) {
            Show show = shows.get(i);
            System.out.println((i + 1) + ". " + show.getStartTime() + " at " + show.getCinemaHall().getName());
        }
        System.out.print("Enter your choice (1-" + shows.size() + "): ");
        int showChoice = getIntInput();

        if (showChoice < 1 || showChoice > shows.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Show selectedShow = shows.get(showChoice - 1);

        // Get available seats for the selected show
        List<ShowSeat> availableSeats = AppContext.showService.getAvailableSeats(selectedShow.getId());
        if (availableSeats.isEmpty()) {
            System.out.println("No seats available for this show.");
            return;
        }

        // Display available seats and let user select
        System.out.println("\nAvailable seats:");
        for (int i = 0; i < availableSeats.size(); i++) {
            ShowSeat seat = availableSeats.get(i);
            System.out.println((i + 1) + ". " + seat.getCinemaHallSeat());
        }

        // Let user select multiple seats
        System.out.print("Enter number of seats to book: ");
        int numSeats = getIntInput();

        if (numSeats < 1 || numSeats > availableSeats.size()) {
            System.out.println("Invalid number of seats.");
            return;
        }

        List<String> selectedSeatIds = new ArrayList<>();
        for (int i = 0; i < numSeats; i++) {
            System.out.print("Select seat " + (i + 1) + " (1-" + availableSeats.size() + "): ");
            int seatChoice = getIntInput();

            if (seatChoice < 1 || seatChoice > availableSeats.size()) {
                System.out.println("Invalid seat choice.");
                return;
            }

            selectedSeatIds.add(availableSeats.get(seatChoice - 1).getId());
        }

        // Create booking
        try {
            Customer customer = (Customer) AppContext.currentUser;
            Booking booking = AppContext.bookingService.createBooking(customer, selectedShow, selectedSeatIds);

            // Process payment
            System.out.println("\nSelect payment method:");
            System.out.println("1. Credit Card");
            System.out.println("2. Cash");
            System.out.print("Enter your choice (1-2): ");
            int paymentChoice = getIntInput();

            PaymentMethod paymentMethod = (paymentChoice == 1) ? PaymentMethod.CREDIT_CARD : PaymentMethod.CASH;

            boolean confirmed = AppContext.bookingService.confirmBooking(booking.getId(), paymentMethod);

            if (confirmed) {
                System.out.println("\nBooking confirmed! Your booking ID is: " + booking.getId());
            } else {
                System.out.println("\nPayment failed. Booking not confirmed.");
            }

        } catch (Exception e) {
            System.out.println("Error creating booking: " + e.getMessage());
        }

        System.out.println("\nPress Enter to continue...");
        AppContext.scanner.nextLine();
    }

    /**
     * Displays the user's bookings.
     */
    private void viewMyBookings() {
        if (!(AppContext.currentUser instanceof Customer)) {
            System.out.println("Only customers can view bookings.");
            return;
        }

        Customer customer = (Customer) AppContext.currentUser;
        List<Booking> bookings = AppContext.bookingService.getBookingsForCustomer(customer.getId());

        if (bookings.isEmpty()) {
            System.out.println("You have no bookings.");
            return;
        }

        System.out.println("\nYour Bookings:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }

        System.out.println("\nPress Enter to continue...");
        AppContext.scanner.nextLine();
    }

    /**
     * Helper method to get integer input from the user.
     */
    private int getIntInput() {
        try {
            int input = AppContext.scanner.nextInt();
            AppContext.scanner.nextLine(); // consume newline
            return input;
        } catch (Exception e) {
            AppContext.scanner.nextLine(); // consume invalid input
            return -1; // invalid input
        }
    }
}