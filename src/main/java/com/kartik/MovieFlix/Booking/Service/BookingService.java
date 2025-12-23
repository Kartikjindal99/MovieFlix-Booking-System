package com.kartik.MovieFlix.Booking.Service;

import com.kartik.MovieFlix.Booking.DTO.BookingDTO;
import com.kartik.MovieFlix.Booking.Entity.Booking;
import com.kartik.MovieFlix.Booking.Entity.BookingStatus;
import com.kartik.MovieFlix.Booking.Entity.Show;
import com.kartik.MovieFlix.Booking.Entity.User;
import com.kartik.MovieFlix.Booking.Repository.BookingRepo;
import com.kartik.MovieFlix.Booking.Repository.ShowRepo;
import com.kartik.MovieFlix.Booking.Repository.UserRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private UserRepo userRepo;

    public Booking creatBooking(BookingDTO bookingDTO) {

        Show show = showRepo.findById(bookingDTO.getShowid())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        if (!isSeatsAvailable(show.getId(), bookingDTO.getNumberOfSeats())) {
            throw new RuntimeException("Not enough seats available");
        }

        if (bookingDTO.getSeatNumber().size() != bookingDTO.getNumberOfSeats()) {
            throw new RuntimeException("Seat count mismatch");
        }

        validateDuplicateSeats(show.getId(), bookingDTO.getSeatNumber());

        User user = userRepo.findById(bookingDTO.getUserid())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setSeatNumbers(bookingDTO.getSeatNumber());
        booking.setPrice(calculateTotalAmount(show.getPrice(), bookingDTO.getNumberOfSeats()));
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);

        return bookingRepo.save(booking);
    }

    public boolean isSeatsAvailable(Long showId, Integer numberOfSeats) {

        Show show = showRepo.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        int bookedSeats = show.getBookings().stream()
                .filter(b -> b.getBookingStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();

        return (show.getTheater().getTheaterCapacity() - bookedSeats) >= numberOfSeats;
    }

    public void validateDuplicateSeats(Long showId, List<String> seatNumbers) {

        Show show = showRepo.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Set<String> occupiedSeats = show.getBookings().stream()
                .filter(b -> b.getBookingStatus() != BookingStatus.CANCELLED)
                .flatMap(b -> b.getSeatNumbers().stream())
                .collect(Collectors.toSet());

        List<String> duplicates = seatNumbers.stream()
                .filter(occupiedSeats::contains)
                .collect(Collectors.toList());

        if (!duplicates.isEmpty()) {
            throw new RuntimeException("Seats already booked: " + duplicates);
        }
    }

    public Double calculateTotalAmount(Double price, Integer numberOfSeats) {
        return price * numberOfSeats;
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    public List<Booking> getShowBookings(Long showId) {
        return bookingRepo.findByShowId(showId);
    }

    public Booking confirmBooking(Long bookingId) {

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getBookingStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Booking not in pending state");
        }

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepo.save(booking);
    }

    public Booking cacelBooking(Long bookingId) {

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        validateCancellation(booking);
        booking.setBookingStatus(BookingStatus.CANCELLED);

        return bookingRepo.save(booking);
    }

    public void validateCancellation(Booking booking) {

        LocalDateTime showTime = booking.getShow().getShowTime();
        LocalDateTime deadline = showTime.minusHours(2);

        if (LocalDateTime.now().isAfter(deadline)) {
            throw new RuntimeException("Cancellation time exceeded");
        }

        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking already cancelled");
        }
    }

    public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {
        return bookingRepo.findByBookingStatus(bookingStatus);
    }
}
