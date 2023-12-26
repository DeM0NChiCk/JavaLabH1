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
public class InfoDto {
    private List<TheaterDto> theaters;
    private List<HotelDto> hotels;
}
