package com.ticketbooking.repositories.impl;

import com.ticketbooking.models.Show;
import com.ticketbooking.models.ShowSeat;
import com.ticketbooking.repositories.ShowRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of ShowRepository.
 */
public class InMemoryShowRepository implements ShowRepository {
    private Map<String, Show> shows = new HashMap<>();
    private Map<String, ShowSeat> showSeats = new HashMap<>();

    // Maps to track relationships
    private Map<String, String> seatToShow = new HashMap<>(); // seatId -> showId

    @Override
    public boolean saveShow(Show show) {
        shows.put(show.getId(), show);
        return true;
    }

    @Override
    public Show getShow(String id) {
        return shows.get(id);
    }

    @Override
    public List<Show> getAllShows() {
        return new ArrayList<>(shows.values());
    }

    @Override
    public List<Show> getShowsForMovie(String movieId) {
        return shows.values().stream()
                .filter(show -> show.getMovie().getId().equals(movieId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Show> getShowsInCinemaHall(String cinemaHallId) {
        return shows.values().stream()
                .filter(show -> show.getCinemaHall().getId().equals(cinemaHallId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveShowSeat(ShowSeat showSeat) {
        showSeats.put(showSeat.getId(), showSeat);
        seatToShow.put(showSeat.getId(), showSeat.getShow().getId());
        return true;
    }

    @Override
    public ShowSeat getShowSeat(String id) {
        return showSeats.get(id);
    }

    @Override
    public List<ShowSeat> getShowSeats(String showId) {
        return showSeats.values().stream()
                .filter(seat -> seatToShow.get(seat.getId()).equals(showId))
                .collect(Collectors.toList());
    }
}