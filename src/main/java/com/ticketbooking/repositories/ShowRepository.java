package com.ticketbooking.repositories;

import com.ticketbooking.models.Show;
import com.ticketbooking.models.ShowSeat;

import java.util.List;

/**
 * Repository interface for Show-related data operations.
 */
public interface ShowRepository {
    boolean saveShow(Show show);

    Show getShow(String id);

    List<Show> getAllShows();

    List<Show> getShowsForMovie(String movieId);

    List<Show> getShowsInCinemaHall(String cinemaHallId);

    boolean saveShowSeat(ShowSeat showSeat);

    ShowSeat getShowSeat(String id);

    List<ShowSeat> getShowSeats(String showId);
}