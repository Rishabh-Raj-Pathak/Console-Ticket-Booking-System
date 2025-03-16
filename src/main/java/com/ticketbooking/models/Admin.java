package com.ticketbooking.models;

/**
 * Represents an admin user who can manage the system.
 */
public class Admin extends User {

    // Constructor
    public Admin(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    // Admin-specific methods
    public boolean addMovie(Movie movie) {
        // Implementation will be added when we create the MovieService
        return true;
    }

    public boolean addShow(Show show) {
        // Implementation will be added when we create the ShowService
        return true;
    }

    public boolean blockUser(Customer customer) {
        // Implementation will be added when we create the UserService
        return true;
    }
}