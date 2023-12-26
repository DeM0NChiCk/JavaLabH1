package ru.itis.hotelservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.hotelservice.dto.HotelDto;
import ru.itis.hotelservice.dto.HotelsPage;
import ru.itis.hotelservice.dto.NewHotelDto;
import ru.itis.hotelservice.exeptions.RestException;
import ru.itis.hotelservice.models.Hotel;
import ru.itis.hotelservice.repositories.HotelRepository;
import ru.itis.hotelservice.service.HotelsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.itis.hotelservice.dto.HotelDto.from;

@Service
@RequiredArgsConstructor
public class HotelsServiceImpl implements HotelsService {

    @Value("${tasks.page.size}")
    private int size;

    private final HotelRepository hotelRepository;

    @Override
    public HotelsPage getHotels(Integer page) {
        PageRequest request = PageRequest.of(page, size, Sort.by("id"));
        Page<Hotel> hotels = hotelRepository.findAll(request);

        return HotelsPage.builder()
                .hotels(from(hotels.getContent()))
                .totalPages(hotels.getTotalPages())
                .build();
    }

    @Override
    public HotelDto getHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(
                        () -> new RestException(HttpStatus.NOT_FOUND, "Hotel with id <" + hotelId + "> not found")
                );
        return from(hotel);
    }

    @Transactional
    @Override
    public HotelDto addHotel(NewHotelDto newHotel) {
        Hotel hotel = Hotel.builder()
                .title(newHotel.getTitle())
                .city(newHotel.getCity())
                .build();

        hotelRepository.save(hotel);

        return from(hotel);
    }

    @Override
    public HotelsPage getHotelByCity(String city) {
        List<Hotel> hotels = hotelRepository.findAllByCity(city);
        List<Hotel> listHotels = new ArrayList<>();

        for (Hotel hotel : hotels) {
            if (Objects.equals(hotel.getCity(), city)) {
                listHotels.add(hotel);
            }
        }

        return HotelsPage.builder()
                .hotels(from(listHotels))
                .totalPages(0)
                .build();
    }
}
