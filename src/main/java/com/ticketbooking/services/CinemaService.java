package com.ticketbooking.services;

import com.ticketbooking.models.Address;
import com.ticketbooking.models.Cinema;
import com.ticketbooking.models.CinemaHall;
import com.ticketbooking.models.City;

import java.util.List;

/**
 * Interface for cinema-related operations.
 */
public interface CinemaService {
    City createCity(String name, String state, String country);

    City getCity(String id);

    List<City> getAllCities();

    Cinema createCinema(String name, Address address, int totalCinemaHalls, String cityId);

    Cinema getCinema(String id);

    List<Cinema> getAllCinemas();

    List<Cinema> getCinemasInCity(String cityId);

    CinemaHall createCinemaHall(String name, int totalSeats, String cinemaId);

    CinemaHall getCinemaHall(String id);

    List<CinemaHall> getAllCinemaHalls(String cinemaId);
}