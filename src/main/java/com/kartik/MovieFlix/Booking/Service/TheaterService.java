package com.kartik.MovieFlix.Booking.Service;

import com.kartik.MovieFlix.Booking.DTO.TheaterDTO;
import com.kartik.MovieFlix.Booking.Entity.Theater;
import com.kartik.MovieFlix.Booking.Repository.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepo theaterRepo;

    public Theater addTheater(TheaterDTO theaterDTO) {

        Theater theater = new Theater();
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());

        return theaterRepo.save(theater);
    }

    public List<Theater> getTheaterByLocation(String location) {

        return theaterRepo.findByTheaterLocation(location)
                .orElseThrow(() ->
                        new RuntimeException("Theater not found at " + location));
    }

    public Theater updateTheater(Long id, TheaterDTO theaterDTO) {

        Theater theater = theaterRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());

        return theaterRepo.save(theater);
    }

    public void deleteTheater(Long id) {
        theaterRepo.deleteById(id);
    }
}
