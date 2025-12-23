package com.kartik.MovieFlix.Booking.Repository;

import com.kartik.MovieFlix.Booking.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {
    Optional<List<Movie>> findByGenre(String genre);
    Optional<List<Movie>> findByLang(String lang);
    Optional<Movie> findByName(String title);
}
