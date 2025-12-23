package com.kartik.MovieFlix.Booking.Controller;

import com.kartik.MovieFlix.Booking.DTO.ShowDTO;
import com.kartik.MovieFlix.Booking.Entity.Show;
import com.kartik.MovieFlix.Booking.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/createshow")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO) {
        return ResponseEntity.ok(
                showService.createShow(showDTO)
        );
    }

    @GetMapping("/getallshows")
    public ResponseEntity<List<Show>> getAllShow() {
        return ResponseEntity.ok(
                showService.getAllShow()
        );
    }

    @GetMapping("/getshowbymovie/{id}")
    public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long id) {
        return ResponseEntity.ok(
                showService.getShowsByMovie(id)
        );
    }

    @GetMapping("/getshowbytheater/{id}")
    public ResponseEntity<List<Show>> getShowsByTheater(@PathVariable Long id) {
        return ResponseEntity.ok(
                showService.getShowsByTheater(id)
        );
    }

    @PutMapping("/updateshow/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> updateShow(
            @PathVariable Long id,
            @RequestBody ShowDTO showDTO) {
        return ResponseEntity.ok(
                showService.updateShow(id, showDTO)
        );
    }

    @DeleteMapping("/deleteshow/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }
}
