package com.kartik.MovieFlix.Booking.Repository;

import com.kartik.MovieFlix.Booking.Entity.Booking;
import com.kartik.MovieFlix.Booking.Entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
    List<Booking> findByUserId(Long id);
    List<Booking> findByShowId(Long id);
    List<Booking> findByBookingStatus(BookingStatus bookingStatus);
}
