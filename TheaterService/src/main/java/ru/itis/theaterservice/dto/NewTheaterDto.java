package ru.itis.theaterservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "New theater", description = "Добавление театра")
public class NewTheaterDto {

    @Schema(description = "Название театра", example = "Ледовый дворец", maxLength = 255)
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
