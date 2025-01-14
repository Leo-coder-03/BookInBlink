package com.jts.movie.services;

import com.jts.movie.Exceptions.MovieAlreadyExist;
import com.jts.movie.converter.MovieConverter;
import com.jts.movie.entities.Movie;
import com.jts.movie.repositories.MovieRepository;
import com.jts.movie.request.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public String addMovie(MovieRequest movieRequest)
    {
        Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());
        if(movieByName !=null && movieByName.getLanguage().equals(movieRequest.getLanguage()))
        {
            throw new MovieAlreadyExist();
        }
        Movie movie = MovieConverter.movieDtoToMovie(movieRequest);
        movieRepository.save(movie);
        return "The movie has been added successfully";
    }

}
