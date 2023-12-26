package ru.itis.theaterservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.theaterservice.dto.NewTheaterDto;
import ru.itis.theaterservice.dto.TheaterDto;
import ru.itis.theaterservice.dto.TheatersPage;
import ru.itis.theaterservice.exeptions.RestException;
import ru.itis.theaterservice.models.Theater;
import ru.itis.theaterservice.repositories.TheaterRepository;
import ru.itis.theaterservice.service.TheatersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.itis.theaterservice.dto.TheaterDto.from;

@Service
@RequiredArgsConstructor
public class TheatersServiceImpl implements TheatersService {

    @Value("${tasks.page.size}")
    private int size;

    private final TheaterRepository theaterRepository;

    @Override
    public TheatersPage getTheaters(Integer page) {
        PageRequest request = PageRequest.of(page, size, Sort.by("id"));
        Page<Theater> theaters = theaterRepository.findAll(request);

        return TheatersPage.builder()
                .theaters(from(theaters.getContent()))
                .totalPages(theaters.getTotalPages())
                .build();
    }

    @Override
    public TheaterDto getTheater(Long hotelId) {
        Theater theater = theaterRepository.findById(hotelId)
                .orElseThrow(
                        () -> new RestException(HttpStatus.NOT_FOUND, "Hotel with id <" + hotelId + "> not found")
                );
        return from(theater);
    }

    @Transactional
    @Override
    public TheaterDto addTheater(NewTheaterDto newHotel) {
        Theater theater = Theater.builder()
                .title(newHotel.getTitle())
                .city(newHotel.getCity())
                .build();

        theaterRepository.save(theater);

        return from(theater);
    }

    @Override
    public TheatersPage getTheaterByCity(String city) {
        List<Theater> theaters = theaterRepository.findAllByCity(city);
        List<Theater> listHotels = new ArrayList<>();

        for (Theater theater : theaters) {
            if (Objects.equals(theater.getCity(), city)) {
                listHotels.add(theater);
            }
        }

        return TheatersPage.builder()
                .theaters(from(listHotels))
                .totalPages(0)
                .build();
    }
}
