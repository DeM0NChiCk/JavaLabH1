package ru.itis.hotelservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(name = "New hotel", description = "Добавление отеля")
public class NewHotelDto {

    @Schema(description = "Название отеля", example = "Космос", maxLength = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String title;

    @Schema(description = "Название города", example = "Москва", maxLength = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String city;

}
