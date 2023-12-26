package ru.itis.csc.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.csc.dto.HotelPage;
import ru.itis.csc.dto.InfoDto;
import ru.itis.csc.dto.TheaterPage;
import ru.itis.csc.service.HotelService;
import ru.itis.csc.service.TheaterService;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class InfoController {

    HotelService hotelService;
    TheaterService theaterService;

    @GetMapping("/info")
    public ResponseEntity<InfoDto> getInfo(@RequestParam("page") Integer page){
        HotelPage hotels = hotelService.getHotels("hotel-api-key", page);
        TheaterPage theaters = theaterService.getTheaters(page, "theater-api-key");

        return ResponseEntity.ok(InfoDto.builder()
                        .theaters(theaters.getTheaters())
                        .hotels(hotels.getHotels())
                .build());
    }

    @GetMapping("/info-by-city")
    public ResponseEntity<InfoDto> getInfoByCity(@RequestParam("page") Integer page,
                                                 @RequestParam("city") String city){
        HotelPage hotels = hotelService.getHotelsByCity(page, "hotel-api-key", city);
        TheaterPage theaters = theaterService.getTheatersByCity(page, "theater-api-key", city);

        return ResponseEntity.ok(InfoDto.builder()
                .theaters(theaters.getTheaters())
                .hotels(hotels.getHotels())
                .build());
    }
}
