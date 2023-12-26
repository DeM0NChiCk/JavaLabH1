package ru.itis.theaterservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.theaterservice.models.Theater;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Theater", description = "Театры")
public class TheaterDto {

    @Schema(description = "Идентификатор театры", example = "10")
    private Long id;

    @Schema(description = "Название театры", example = "Ледовый дворец", maxLength = 255)
    private String title;

    @Schema(description = "Название города", example = "Москва", maxLength = 255)
    private String city;

    public static TheaterDto from(Theater hotel) {
        TheaterDto result = TheaterDto.builder()
                .id(hotel.getId())
                .title(hotel.getTitle())
                .city(hotel.getCity())
                .build();

        return result;
    }

    public static List<TheaterDto> from(List<Theater> hotels) {
        return hotels.stream()
                .map(TheaterDto::from)
                .collect(Collectors.toList());
    }
}
