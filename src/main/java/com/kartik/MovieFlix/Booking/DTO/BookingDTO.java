package com.kartik.MovieFlix.Booking.DTO;

import com.kartik.MovieFlix.Booking.Entity.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDTO {
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private Double price;
    private BookingStatus bookingStatus;
    private List<String> seatNumber;
    private Long userid;
    private Long showid;
}
