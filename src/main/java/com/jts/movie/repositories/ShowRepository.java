package com.jts.movie.repositories;

import com.jts.movie.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
