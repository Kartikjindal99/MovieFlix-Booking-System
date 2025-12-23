package com.kartik.MovieFlix.Booking.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private Double price;
    private BookingStatus bookingStatus;

    @ElementCollection(fetch = FetchType.EAGER)//it will create a seperate table in db by booking seat number with two colum bookning id and seat number
    @CollectionTable(name="booking_seat_numbers")
    private List<String> seatNumbers;

    @ManyToOne(fetch = FetchType.EAGER)//why fetch type eager i want user info also when booking load
   @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
}