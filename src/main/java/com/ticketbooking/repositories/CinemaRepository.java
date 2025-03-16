package com.ticketbooking.repositories;

import com.ticketbooking.models.Cinema;
import com.ticketbooking.models.CinemaHall;
import com.ticketbooking.models.City;

import java.util.List;

/**
 * Repository interface for Cinema-related data operations.
 */
public interface CinemaRepository {
    boolean saveCity(City city);

    City getCity(String id);

    List<City> getAllCities();

    boolean saveCinema(Cinema cinema);

    Cinema getCinema(String id);

    List<Cinema> getAllCinemas();

    List<Cinema> getCinemasInCity(String cityId);

    boolean saveCinemaHall(CinemaHall cinemaHall, String cinemaId);

    CinemaHall getCinemaHall(String id);

    List<CinemaHall> getAllCinemaHalls(String cinemaId);
}