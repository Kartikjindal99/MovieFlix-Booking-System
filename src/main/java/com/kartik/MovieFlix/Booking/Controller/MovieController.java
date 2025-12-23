package com.kartik.MovieFlix.Booking.Controller;

import com.kartik.MovieFlix.Booking.DTO.MovieDTO;
import com.kartik.MovieFlix.Booking.Entity.Movie;
import com.kartik.MovieFlix.Booking.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addmovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(
                movieService.addMovie(movieDTO)
        );
    }

    @GetMapping("/getallmovies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(
                movieService.getAllMovies()
        );
    }

    @GetMapping("/getmoviebygenre")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(
                movieService.getMovieByGenre(genre)
        );
    }

    @GetMapping("/getmoviebylang")
    public ResponseEntity<List<Movie>> getMovieByLang(@RequestParam String lang) {
        return ResponseEntity.ok(
                movieService.getMovieByLang(lang)
        );
    }

    @GetMapping("/getmoviebytitle")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title) {
        return ResponseEntity.ok(
                movieService.getMovieByTitle(title)
        );
    }

    @PutMapping("/updatemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable Long id,
            @RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(
                movieService.updateMovie(id, movieDTO)
        );
    }

    @DeleteMapping("/deletemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}

