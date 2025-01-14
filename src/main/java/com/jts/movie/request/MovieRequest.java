package com.jts.movie.request;

import com.jts.movie.enums.Genre;
import com.jts.movie.enums.Language;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Data
public class MovieRequest {
    private String movieName;
    private Integer duration;
    private Double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;
}
