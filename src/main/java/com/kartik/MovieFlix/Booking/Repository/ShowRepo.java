package com.kartik.MovieFlix.Booking.Repository;

import com.kartik.MovieFlix.Booking.Entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepo extends JpaRepository<Show,Long> {
    Optional<List<Show>> findByMovieId(Long movieid);
    Optional<List<Show>> findByTheaterId(Long theaterid);
}
