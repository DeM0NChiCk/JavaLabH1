package ru.itis.hotelservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.hotelservice.models.Hotel;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Hotel", description = "Отели")
public class HotelDto {

    @Schema(description = "Идентификатор отеля", example = "10")
    private Long id;

    @Schema(description = "Название отеля", example = "Космос", maxLength = 255)
    private String title;

    @Schema(description = "Название города", example = "Москва", maxLength = 255)
    private String city;

    public static HotelDto from(Hotel hotel) {
        HotelDto result = HotelDto.builder()
                .id(hotel.getId())
                .title(hotel.getTitle())
                .city(hotel.getCity())
                .build();

        return result;
    }

    public static List<HotelDto> from(List<Hotel> hotels) {
        return hotels.stream()
                .map(HotelDto::from)
                .collect(Collectors.toList());
    }
}
