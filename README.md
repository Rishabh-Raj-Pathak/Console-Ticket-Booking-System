# Movie Ticket Booking System

A Java-based console application for booking movie tickets. This project is built to learn Object-Oriented Programming concepts.

## Project Overview

This Movie Ticket Booking System allows users to:

- Browse movies by title, genre, language, and release date
- View available shows at different cinemas
- Book tickets for selected shows
- Select seats from available seating arrangements
- Make payments for bookings
- Cancel bookings and receive refunds (within a time limit)

## Features

1. List cities where cinemas are located
2. Each cinema can have multiple halls, and each hall will run one show at a time
3. Each movie will have multiple shows
4. Customers can search movies based on title, genre, language, release date, and city
5. Customers can select a show and book tickets
6. The system will show seating arrangements in the hall, and customers can select their seats
7. Customers can pay for tickets and cancel their tickets
8. The system can send notifications for new movies, bookings, and cancellations
9. Customers can pay with credit cards or cash
10. The system ensures no two customers can reserve the same seat
11. Admin can add new movies and cancel existing ones

## Learning Goals

This project is designed to help learn:

- Java programming fundamentals
- Object-Oriented Programming concepts:
  - Encapsulation
  - Inheritance
  - Polymorphism
  - Abstraction
- Design patterns
- Project planning and implementation

## Project Structure

The project follows a standard Java package structure:

```
src/
├── main/
│   └── java/
│       └── com/
│           └── ticketbooking/
│               ├── models/       # Domain models
│               ├── services/     # Business logic
│               ├── repositories/ # Data access
│               ├── utils/        # Utility classes
│               └── Main.java     # Application entry point
```

## How to Run

1. Ensure you have Java JDK 8 or higher installed
2. Compile the project
3. Run the Main class

## Development Roadmap

1. Design the class structure
2. Implement the core models
3. Develop the business logic services
4. Create a simple console UI
5. Implement data persistence
6. Add advanced features (notifications, etc.)
