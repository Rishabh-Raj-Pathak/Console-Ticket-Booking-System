package com.ticketbooking.repositories.impl;

import com.ticketbooking.models.Cinema;
import com.ticketbooking.models.CinemaHall;
import com.ticketbooking.models.City;
import com.ticketbooking.repositories.CinemaRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of CinemaRepository.
 */
public class InMemoryCinemaRepository implements CinemaRepository {
    private Map<String, City> cities = new HashMap<>();
    private Map<String, Cinema> cinemas = new HashMap<>();
    private Map<String, CinemaHall> cinemaHalls = new HashMap<>();

    // Maps to track relationships
    private Map<String, String> cinemaToCity = new HashMap<>(); // cinemaId -> cityId
    private Map<String, String> hallToCinema = new HashMap<>(); // hallId -> cinemaId

    @Override
    public boolean saveCity(City city) {
        cities.put(city.getId(), city);
        return true;
    }

    @Override
    public City getCity(String id) {
        return cities.get(id);
    }

    @Override
    public List<City> getAllCities() {
        return new ArrayList<>(cities.values());
    }

    @Override
    public boolean saveCinema(Cinema cinema) {
        cinemas.put(cinema.getId(), cinema);
        return true;
    }

    @Override
    public Cinema getCinema(String id) {
        return cinemas.get(id);
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return new ArrayList<>(cinemas.values());
    }

    @Override
    public List<Cinema> getCinemasInCity(String cityId) {
        return cinemas.values().stream()
                .filter(cinema -> cinemaToCity.get(cinema.getId()).equals(cityId))
                .collect(Collectors.toList());
    }

   
    @Override
    public boolean saveCinemaHall(CinemaHall cinemaHall, String cinemaId) {
        cinemaHalls.put(cinemaHall.getId(), cinemaHall);
        hallToCinema.put(cinemaHall.getId(), cinemaId);

        // Add hall to cinema
        Cinema cinema = cinemas.get(cinemaId);
        if (cinema != null) {
            cinema.addHall(cinemaHall);
        }

        return true;
    }

    @Override
    public CinemaHall getCinemaHall(String id) {
        return cinemaHalls.get(id);
    }

    @Override
    public List<CinemaHall> getAllCinemaHalls(String cinemaId) {
        return cinemaHalls.values().stream()
                .filter(hall -> hallToCinema.get(hall.getId()).equals(cinemaId))
                .collect(Collectors.toList());
    }
}