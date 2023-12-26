package ru.itis.csc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheaterPage {
    private List<TheaterDto> theaters;

    private Integer totalPages;
}
