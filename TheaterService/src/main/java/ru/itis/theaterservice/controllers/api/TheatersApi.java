package ru.itis.theaterservice.controllers.api;

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
import ru.itis.theaterservice.dto.NewTheaterDto;
import ru.itis.theaterservice.dto.StandardResponseDto;
import ru.itis.theaterservice.dto.TheaterDto;
import ru.itis.theaterservice.dto.TheatersPage;
import ru.itis.theaterservice.validation.dto.ValidationErrorsDto;

@Tags(
        value =
        @Tag(name = "Theaters")
)
@RequestMapping("/api/theaters")
public interface TheatersApi {

    @Operation(summary = "Получение списка театров", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос успешно обработан",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TheatersPage.class))
                    }
            )
    })
    @GetMapping
    ResponseEntity<TheatersPage> getTheaters(@Parameter(description = "Номер страницы", example = "1")
                                       @RequestParam("page") Integer page);

    @Operation(summary = "Получение информации о театре", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TheaterDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Театр не найден",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @GetMapping("/{theater_id}")
    ResponseEntity<TheaterDto> getTheater(@Parameter(description = "Идентификатор отеля", example = "1")
                                     @PathVariable("theater_id") Long theater_id);

    @Operation(summary = "Добавление театра", description = "Доступно только менеджерам")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Театр добавлен успешно",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TheaterDto.class))
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
    ResponseEntity<TheaterDto> addTheater(@RequestBody @Valid NewTheaterDto newTheater);

    @Operation(summary = "Получение списка театров по городу", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TheatersPage.class))
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
    ResponseEntity<TheatersPage> getTheaterByCity(
            @Parameter(description = "Название города", example = "Москва") @RequestParam("city") String city
    );

}
