package com.jts.movie.services;

import com.jts.movie.Exceptions.MovieDoesNotExists;
import com.jts.movie.Exceptions.TheaterDoesNotExists;
import com.jts.movie.converter.ShowConverter;
import com.jts.movie.entities.Movie;
import com.jts.movie.entities.Show;
import com.jts.movie.entities.Theater;
import com.jts.movie.repositories.MovieRepository;
import com.jts.movie.repositories.ShowRepository;
import com.jts.movie.repositories.TheaterRepository;
import com.jts.movie.request.ShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ShowRepository showRepository;
    public String addShow(ShowRequest showRequest)
    {
        Show show =  ShowConverter.showDtoToShow(showRequest);

        Optional<Movie> movieOpt = movieRepository.findById(showRequest.getMovieId());
        if(movieOpt.isEmpty())
        {
            throw new MovieDoesNotExists();
        }
        Optional<Theater> theaterOpt = theaterRepository.findById(showRequest.getMovieId());
        if(theaterOpt.isEmpty())
        {
            throw new TheaterDoesNotExists();
        }
        Theater theater = theaterOpt.get();
        Movie movie = movieOpt.get();
        show.setMovie(movie);
        show.setTheater(theater);
        show = showRepository.save(show);
        movie.getShows().add(show);
        theater.getShowList().add(show);
        movieRepository.save(movie);
        theaterRepository.save(theater);
        return "Show has been added successfully";
    }
    }
