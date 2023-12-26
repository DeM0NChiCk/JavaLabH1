package ru.itis.theaterservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.theaterservice.dto.TheaterDto;
import ru.itis.theaterservice.dto.TheatersPage;
import ru.itis.theaterservice.dto.NewTheaterDto;
import ru.itis.theaterservice.controllers.api.TheatersApi;
import ru.itis.theaterservice.service.TheatersService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TheatersController implements TheatersApi {

    TheatersService theaterService;

    @Override
    public ResponseEntity<TheatersPage> getTheaters(Integer page) {
        return ResponseEntity.ok(theaterService.getTheaters(page));
    }

    @Override
    public ResponseEntity<TheaterDto> getTheater(Long theater_id) {
        return ResponseEntity.ok(theaterService.getTheater(theater_id));
    }

    @Override
    public ResponseEntity<TheaterDto> addTheater(NewTheaterDto newTheater) {
        return ResponseEntity.status(201).body(theaterService.addTheater(newTheater));
    }

    @Override
    public ResponseEntity<TheatersPage> getTheaterByCity(String city) {
        return ResponseEntity.ok(theaterService.getTheaterByCity(city));
    }
}
