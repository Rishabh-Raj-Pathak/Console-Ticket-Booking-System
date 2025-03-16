package com.ticketbooking;

/**
 * Main entry point for the Movie Ticket Booking System.
 * This class initializes the application and handles the main program flow.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Welcome to Movie Ticket Booking System!");
        System.out.println("======================================");
        
        // Initialize the system components
        Initializer.initializeSystem();
        
        // Create sample data
        SampleDataCreator.createSampleData();
        
        // Start the user interface
        MenuHandler menuHandler = new MenuHandler();
        menuHandler.showMainMenu();
        
        // Close resources
        AppContext.scanner.close();
        
        System.out.println("Thank you for using Movie Ticket Booking System!");
    }
}