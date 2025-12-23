package com.kartik.MovieFlix.Booking.Service;

import com.kartik.MovieFlix.Booking.DTO.ShowDTO;
import com.kartik.MovieFlix.Booking.Entity.Booking;
import com.kartik.MovieFlix.Booking.Entity.Movie;
import com.kartik.MovieFlix.Booking.Entity.Show;
import com.kartik.MovieFlix.Booking.Entity.Theater;
import com.kartik.MovieFlix.Booking.Repository.MovieRepo;
import com.kartik.MovieFlix.Booking.Repository.ShowRepo;
import com.kartik.MovieFlix.Booking.Repository.TheaterRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private TheaterRepo theaterRepo;

    public Show createShow(ShowDTO showDTO) {

        Movie movie = movieRepo.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Theater theater = theaterRepo.findById(showDTO.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        Show show = new Show();
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);

        return showRepo.save(show);
    }

    public List<Show> getAllShow() {
        return showRepo.findAll();
    }

    public List<Show> getShowsByMovie(Long movieId) {

        return showRepo.findByMovieId(movieId)
                .orElseThrow(() ->
                        new RuntimeException("No shows found for movie"));
    }

    public List<Show> getShowsByTheater(Long theaterId) {

        return showRepo.findByTheaterId(theaterId)
                .orElseThrow(() ->
                        new RuntimeException("No shows found for theater"));
    }

    public Show updateShow(Long id, ShowDTO showDTO) {

        Show show = showRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Movie movie = movieRepo.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Theater theater = theaterRepo.findById(showDTO.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);

        return showRepo.save(show);
    }

    public void deleteShow(Long id) {

        Show show = showRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        if (!show.getBookings().isEmpty()) {
            throw new RuntimeException("Cannot delete show with bookings");
        }

        showRepo.deleteById(id);
    }
}
