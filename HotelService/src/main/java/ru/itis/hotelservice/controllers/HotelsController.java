package ru.itis.hotelservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hotelservice.controllers.api.HotelsApi;
import ru.itis.hotelservice.dto.HotelDto;
import ru.itis.hotelservice.dto.HotelsPage;
import ru.itis.hotelservice.dto.NewHotelDto;
import ru.itis.hotelservice.service.HotelsService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelsController implements HotelsApi {

    HotelsService hotelsService;

    @Override
    public ResponseEntity<HotelsPage> getHotels(Integer page) {
        return ResponseEntity.ok(hotelsService.getHotels(page));
    }

    @Override
    public ResponseEntity<HotelDto> getHotel(Long hotel_id) {
        return ResponseEntity.ok(hotelsService.getHotel(hotel_id));
    }

    @Override
    public ResponseEntity<HotelDto> addTask(NewHotelDto newHotel) {
        return ResponseEntity.status(201).body(hotelsService.addHotel(newHotel));
    }

    @Override
    public ResponseEntity<HotelsPage> getHotelByCity(String city) {
        return ResponseEntity.ok(hotelsService.getHotelByCity(city));
    }
}
