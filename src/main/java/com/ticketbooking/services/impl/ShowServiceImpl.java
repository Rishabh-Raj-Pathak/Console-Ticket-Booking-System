package com.ticketbooking.services.impl;

import com.ticketbooking.models.*;
import com.ticketbooking.repositories.CinemaRepository;
import com.ticketbooking.repositories.ShowRepository;
import com.ticketbooking.services.ShowService;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of ShowService interface.
 */
public class ShowServiceImpl implements ShowService {
    private ShowRepository showRepository;
    private CinemaRepository cinemaRepository;

    public ShowServiceImpl(ShowRepository showRepository, CinemaRepository cinemaRepository) {
        this.showRepository = showRepository;
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public Show createShow(Movie movie, CinemaHall cinemaHall, Date startTime, Date endTime) {
        String id = UUID.randomUUID().toString();
        Show show = new Show(id, startTime, endTime, movie, cinemaHall);
        showRepository.saveShow(show);

        // Create show seats for each cinema hall seat
        for (CinemaHallSeat cinemaHallSeat : cinemaHall.getSeats()) {
            createShowSeat(show, cinemaHallSeat.getId());
        }

        return show;
    }

    @Override
    public Show getShow(String id) {
        return showRepository.getShow(id);
    }

    @Override
    public List<Show> getAllShows() {
        return showRepository.getAllShows();
    }

    @Override
    public List<Show> getShowsForMovie(String movieId) {
        return showRepository.getShowsForMovie(movieId);
    }

    @Override
    public List<Show> getShowsInCinemaHall(String cinemaHallId) {
        return showRepository.getShowsInCinemaHall(cinemaHallId);
    }

    @Override
    public ShowSeat createShowSeat(Show show, String cinemaHallSeatId) {
        CinemaHallSeat cinemaHallSeat = cinemaRepository.getCinemaHall(show.getCinemaHall().getId())
                .getSeats().stream()
                .filter(seat -> seat.getId().equals(cinemaHallSeatId))
                .findFirst()
                .orElse(null);

        if (cinemaHallSeat == null) {
            throw new IllegalArgumentException("Cinema hall seat not found");
        }

        String id = UUID.randomUUID().toString();
        ShowSeat showSeat = new ShowSeat(id, cinemaHallSeat, show);
        showRepository.saveShowSeat(showSeat);
        show.addShowSeat(showSeat);

        return showSeat;
    }

    @Override
    public List<ShowSeat> getShowSeats(String showId) {
        return showRepository.getShowSeats(showId);
    }

    @Override
    public List<ShowSeat> getAvailableSeats(String showId) {
        return showRepository.getShowSeats(showId).stream()
                .filter(seat -> !seat.isReserved())
                .collect(Collectors.toList());
    }
}