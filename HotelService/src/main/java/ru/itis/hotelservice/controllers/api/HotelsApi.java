package ru.itis.hotelservice.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hotelservice.dto.HotelDto;
import ru.itis.hotelservice.dto.HotelsPage;
import ru.itis.hotelservice.dto.NewHotelDto;
import ru.itis.hotelservice.dto.StandardResponseDto;
import ru.itis.hotelservice.validation.dto.ValidationErrorsDto;

@Tags(
        value =
        @Tag(name = "Hotels")
)
@RequestMapping("/api/hotels")
public interface HotelsApi {

    @Operation(summary = "Получение списка отелей", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос успешно обработан",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HotelsPage.class))
                    }
            )
    })
    @GetMapping
    ResponseEntity<HotelsPage> getHotels(@Parameter(description = "Номер страницы", example = "1")
                                       @RequestParam("page") Integer page);

    @Operation(summary = "Получение информации об отеле", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HotelDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Отель не найден",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @GetMapping("/{hotel_id}")
    ResponseEntity<HotelDto> getHotel(@Parameter(description = "Идентификатор отеля", example = "1")
                                     @PathVariable("hotel_id") Long hotel_id);

    @Operation(summary = "Добавление отеля", description = "Доступно только менеджерам")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Отель добавлен успешно",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HotelDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ValidationErrorsDto.class))
                    }
            )
    })
    @PostMapping
    ResponseEntity<HotelDto> addTask(@RequestBody @Valid NewHotelDto newHotel);

    @Operation(summary = "Получение списка отелей по городу", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HotelsPage.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Город не найден",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @GetMapping("/city")
    ResponseEntity<HotelsPage> getHotelByCity(
            @Parameter(description = "Название города", example = "Москва") @RequestParam("city") String city
    );

}
