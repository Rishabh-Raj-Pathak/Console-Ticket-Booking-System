package com.ticketbooking.services.impl;

import com.ticketbooking.models.Address;
import com.ticketbooking.models.Cinema;
import com.ticketbooking.models.CinemaHall;
import com.ticketbooking.models.City;
import com.ticketbooking.repositories.CinemaRepository;
import com.ticketbooking.services.CinemaService;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of CinemaService interface.
 */
public class CinemaServiceImpl implements CinemaService {
    private CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public City createCity(String name, String state, String country) {
        String id = UUID.randomUUID().toString();
        City city = new City(id, name, state, country);
        cinemaRepository.saveCity(city);
        return city;
    }

    @Override
    public City getCity(String id) {
        return cinemaRepository.getCity(id);
    }

    @Override
    public List<City> getAllCities() {
        return cinemaRepository.getAllCities();
    }

    @Override
    public Cinema createCinema(String name, Address address, int totalCinemaHalls, String cityId) {
        String id = UUID.randomUUID().toString();
        Cinema cinema = new Cinema(id, name, address, totalCinemaHalls);

        // Add cinema to city
        City city = cinemaRepository.getCity(cityId);
        if (city != null) {
            city.addCinema(cinema);
        }

        cinemaRepository.saveCinema(cinema);
        return cinema;
    }

    @Override
    public Cinema getCinema(String id) {
        return cinemaRepository.getCinema(id);
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.getAllCinemas();
    }

    @Override
    public List<Cinema> getCinemasInCity(String cityId) {
        return cinemaRepository.getCinemasInCity(cityId);
    }

    @Override
    public CinemaHall createCinemaHall(String name, int totalSeats, String cinemaId) {
        String id = UUID.randomUUID().toString();
        CinemaHall cinemaHall = new CinemaHall(id, name, totalSeats);
        cinemaRepository.saveCinemaHall(cinemaHall, cinemaId);
        return cinemaHall;
    }

    @Override
    public CinemaHall getCinemaHall(String id) {
        return cinemaRepository.getCinemaHall(id);
    }

    @Override
    public List<CinemaHall> getAllCinemaHalls(String cinemaId) {
        return cinemaRepository.getAllCinemaHalls(cinemaId);
    }
}