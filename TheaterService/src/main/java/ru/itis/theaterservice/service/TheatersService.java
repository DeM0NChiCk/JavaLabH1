package ru.itis.theaterservice.service;

import ru.itis.theaterservice.dto.TheaterDto;
import ru.itis.theaterservice.dto.TheatersPage;
import ru.itis.theaterservice.dto.NewTheaterDto;

public interface TheatersService {
    TheatersPage getTheaters(Integer page);

    TheaterDto getTheater(Long hotelId);

    TheaterDto addTheater(NewTheaterDto newHotel);

    TheatersPage getTheaterByCity(String city);
}
