package ru.itis.hotelservice.service;

import ru.itis.hotelservice.dto.HotelDto;
import ru.itis.hotelservice.dto.HotelsPage;
import ru.itis.hotelservice.dto.NewHotelDto;

public interface HotelsService {
    HotelsPage getHotels(Integer page);

    HotelDto getHotel(Long hotelId);

    HotelDto addHotel(NewHotelDto newHotel);

    HotelsPage getHotelByCity(String city);
}
