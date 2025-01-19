package com.jts.movie.services;

import com.jts.movie.Exceptions.TheaterIsExist;
import com.jts.movie.Exceptions.TheaterIsNotExist;
import com.jts.movie.convertor.TheaterConvertor;
import com.jts.movie.entities.Theater;
import com.jts.movie.entities.TheaterSeat;
import com.jts.movie.enums.SeatType;
import com.jts.movie.repositories.TheaterRepository;
import com.jts.movie.request.TheaterRequest;
import com.jts.movie.request.TheaterSeatRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;
    public String addTheater(TheaterRequest theaterRequest) throws TheaterIsExist
    {
        if(theaterRepository.findByAddress(theaterRequest.getAddress())!=null)
        {
            throw new TheaterIsExist();
        }
        Theater theater = TheaterConvertor.theaterDtoToTheater(theaterRequest);
        theaterRepository.save(theater);
        return "Theater has been saved successfully";
    }
    public String addTheaterSeat(TheaterSeatRequest entryDto)throws TheaterIsNotExist
    {
        if(theaterRepository.findByAddress(entryDto.getAddress())==null)
            throw new TheaterIsNotExist();
        Integer noOfSeatsInRow = entryDto.getNoOfSeatInRow();
        Integer noOfPremiumSeats = entryDto.getNoOfPremiumSeat();
        Integer noOfClassicSeat = entryDto.getNoOfClassicSeat();
        String address = entryDto.getAddress();

        Theater theater = theaterRepository.findByAddress(address);
        List<TheaterSeat> seatList = theater.getTheaterSeatList();
        int counter = 1;
        int fill = 0;
        char ch = 'A';
        for(int i=1;i<=noOfClassicSeat;i++)
        {
            String seatNo = Integer.toString(counter)+ch;
            ch++;
            fill++;
            if(fill == noOfSeatsInRow)
            {
                fill = 0;
                counter++;
                ch = 'A';
            }
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }
        for(int i=1;i<=noOfPremiumSeats;i++)
        {
            String seatNo = Integer.toString(counter)+ch;
            ch++;
            fill++;
            if(fill == noOfSeatsInRow)
            {
                fill = 0;
                counter++;
                ch = 'A';
            }
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }
        theaterRepository.save(theater);
        return "Theater seats have been added successfully";
    }
}
