package ru.itis.hotelservice.exeptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException extends RuntimeException {
    private final HttpStatus status;

    public RestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
