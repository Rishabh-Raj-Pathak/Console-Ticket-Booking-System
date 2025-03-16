package com.ticketbooking;

import com.ticketbooking.models.*;
import com.ticketbooking.models.enums.SeatType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Responsible for creating sample data for testing.
 */
public class SampleDataCreator {

    // Date formatter for sample dates
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Create sample data including an admin, cities, cinemas, halls, seats, movies,
     * and shows.
     */
    public static void createSampleData() {
        try {
            // Create an admin user
            Admin admin = AppContext.userService.createAdmin("Admin", "admin@example.com", "1234567890", "admin123");

            // Create a customer user for testing
            Customer customer = AppContext.userService.createCustomer("John Doe", "john@example.com", "9876543210",
                    "password123");

            // Create cities
            City newYork = AppContext.cinemaService.createCity("New York", "NY", "USA");
            City losAngeles = AppContext.cinemaService.createCity("Los Angeles", "CA", "USA");

            // Create cinemas
            Address address1 = new Address("123 Main St", "New York", "NY", "10001", "USA");
            Cinema cinema1 = AppContext.cinemaService.createCinema("AMC Empire 25", address1, 3, newYork.getId());

            Address address2 = new Address("456 Broadway", "New York", "NY", "10002", "USA");
            Cinema cinema2 = AppContext.cinemaService.createCinema("Regal Cinemas", address2, 2, newYork.getId());

            Address address3 = new Address("789 Hollywood Blvd", "Los Angeles", "CA", "90001", "USA");
            Cinema cinema3 = AppContext.cinemaService.createCinema("TCL Chinese Theatre", address3, 2,
                    losAngeles.getId());

            // Create cinema halls for each cinema
            CinemaHall hall1 = AppContext.cinemaService.createCinemaHall("Hall 1", 50, cinema1.getId());
            CinemaHall hall2 = AppContext.cinemaService.createCinemaHall("Hall 2", 40, cinema1.getId());
            CinemaHall hall3 = AppContext.cinemaService.createCinemaHall("Hall 3", 60, cinema1.getId());
            CinemaHall hall4 = AppContext.cinemaService.createCinemaHall("Hall 1", 45, cinema2.getId());
            CinemaHall hall5 = AppContext.cinemaService.createCinemaHall("Hall 2", 55, cinema2.getId());
            CinemaHall hall6 = AppContext.cinemaService.createCinemaHall("Hall 1", 70, cinema3.getId());
            CinemaHall hall7 = AppContext.cinemaService.createCinemaHall("Hall 2", 80, cinema3.getId());

            // Create seats for each hall
            createSeatsForHall(hall1);
            createSeatsForHall(hall2);
            createSeatsForHall(hall3);
            createSeatsForHall(hall4);
            createSeatsForHall(hall5);
            createSeatsForHall(hall6);
            createSeatsForHall(hall7);

            // Create movies
            Movie movie1 = AppContext.movieService.createMovie("Inception", "A mind-bending thriller", 148, "English",
                    dateFormat.parse("2010-07-16"), "Sci-Fi");
            Movie movie2 = AppContext.movieService.createMovie("The Dark Knight", "Batman faces the Joker", 152,
                    "English", dateFormat.parse("2008-07-18"), "Action");
            Movie movie3 = AppContext.movieService.createMovie("Avengers: Endgame",
                    "Epic conclusion to the Infinity Saga", 181, "English", dateFormat.parse("2019-04-26"), "Action");

            // Create shows (using sample dates in yyyy-MM-dd format)
            Show show1 = AppContext.showService.createShow(movie1, hall1, dateFormat.parse("2023-03-15"),
                    dateFormat.parse("2023-03-15"));
            Show show2 = AppContext.showService.createShow(movie2, hall2, dateFormat.parse("2023-03-15"),
                    dateFormat.parse("2023-03-15"));
            Show show3 = AppContext.showService.createShow(movie3, hall3, dateFormat.parse("2023-03-16"),
                    dateFormat.parse("2023-03-16"));

            System.out.println("Sample data created successfully!");
            System.out.println("Test user credentials: john@example.com / password123");

        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }

    /**
     * Create seats for a given cinema hall.
     * Assumes 10 rows, with seatsPerRow = totalSeats / 10.
     * The first two rows are designated as Premium; remaining rows are Regular.
     */
    private static void createSeatsForHall(CinemaHall hall) {
        int seatsPerRow = hall.getTotalSeats() / 10;
        for (int row = 1; row <= 10; row++) {
            for (int seatNumber = 1; seatNumber <= seatsPerRow; seatNumber++) {
                SeatType seatType = (row <= 2) ? SeatType.PREMIUM : SeatType.REGULAR;
                CinemaHallSeat seat = new CinemaHallSeat(UUID.randomUUID().toString(), row, seatNumber, seatType,
                        10.00);
                hall.addSeat(seat);
            }
        }
    }
}