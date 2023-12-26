package ru.itis.csc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.csc.dto.HotelPage;

@FeignClient(name = "hotel-service", url = "${feign.hotel-service.url}")
public interface HotelService {
    @GetMapping(value = "/api/hotels", consumes = MediaType.APPLICATION_JSON_VALUE)
    HotelPage getHotels(@RequestParam("api-key") String apiKey, @RequestParam("page") Integer page);

    @GetMapping(value = "/api/hotels/city", consumes = MediaType.APPLICATION_JSON_VALUE)
    HotelPage getHotelsByCity(
            @RequestParam("page") Integer page,
            @RequestParam("api-key") String apiKey,
            @RequestParam("city") String city);
}
