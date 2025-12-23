package com.kartik.MovieFlix.Booking.Service;

import com.kartik.MovieFlix.Booking.DTO.MovieDTO;
import com.kartik.MovieFlix.Booking.Entity.Movie;
import com.kartik.MovieFlix.Booking.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    public Movie addMovie(MovieDTO movieDTO) {

        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLang(movieDTO.getLang());

        return movieRepo.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public List<Movie> getMovieByGenre(String genre) {

        return movieRepo.findByGenre(genre)
                .orElseThrow(() ->
                        new RuntimeException("No movie found for genre " + genre));
    }

    public List<Movie> getMovieByLang(String lang) {

        return movieRepo.findByLang(lang)
                .orElseThrow(() ->
                        new RuntimeException("No movie found for language " + lang));
    }

    public Movie getMovieByTitle(String title) {

        return movieRepo.findByName(title)
                .orElseThrow(() ->
                        new RuntimeException("Movie not found with title " + title));
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {

        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLang(movieDTO.getLang());

        return movieRepo.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepo.deleteById(id);
    }
}
