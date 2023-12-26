package ru.itis.csc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.csc.dto.TheaterPage;

@FeignClient(name = "theater-service", url = "${feign.theater-service.url}")
public interface TheaterService {
    @GetMapping(value = "/api/theaters", consumes = MediaType.APPLICATION_JSON_VALUE)
    TheaterPage getTheaters(@RequestParam("page") Integer page, @RequestParam("api-key") String apiKey);

    @GetMapping(value = "/api/theaters/city", consumes = MediaType.APPLICATION_JSON_VALUE)
    TheaterPage getTheatersByCity(
            @RequestParam("page") Integer page,
            @RequestParam("api-key") String apiKey,
            @RequestParam("city") String city);
}
