package com.jts.movie.converter;

import com.jts.movie.entities.Show;
import com.jts.movie.request.ShowRequest;

public class ShowConverter {
    public static Show showDtoToShow(ShowRequest showRequest)
    {
        Show show = Show.builder()
                .time(showRequest.getShowStartTime())
                .date(showRequest.getShowDate())
                .build();
        return show;
    }
}
