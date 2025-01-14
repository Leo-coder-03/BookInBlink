package com.jts.movie.repositories;

import com.jts.movie.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
Movie findByMovieName(String name);

}

