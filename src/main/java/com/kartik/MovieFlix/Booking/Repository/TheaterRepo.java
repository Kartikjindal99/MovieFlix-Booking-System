package com.kartik.MovieFlix.Booking.Repository;

import com.kartik.MovieFlix.Booking.Entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRepo extends JpaRepository<Theater,Long> {
    Optional<List<Theater>> findByTheaterLocation(String location);
}
