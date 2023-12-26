package ru.itis.theaterservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Страница с театрами")
public class TheatersPage {
    @Schema(description = "Список театров")
    private List<TheaterDto> theaters;

    @Schema(description = "Общее кол-во страниц с театрами", example = "10")
    private Integer totalPages;
}
