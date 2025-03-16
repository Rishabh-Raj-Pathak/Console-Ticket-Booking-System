package com.ticketbooking.services;

import com.ticketbooking.models.CinemaHall;
import com.ticketbooking.models.Movie;
import com.ticketbooking.models.Show;
import com.ticketbooking.models.ShowSeat;

import java.util.Date;
import java.util.List;

/**
 * Interface for show-related operations.
 */
public interface ShowService {
    Show createShow(Movie movie, CinemaHall cinemaHall, Date startTime, Date endTime);

    Show getShow(String id);

    List<Show> getAllShows();

    List<Show> getShowsForMovie(String movieId);

    List<Show> getShowsInCinemaHall(String cinemaHallId);

    ShowSeat createShowSeat(Show show, String cinemaHallSeatId);

    List<ShowSeat> getShowSeats(String showId);

    List<ShowSeat> getAvailableSeats(String showId);
}