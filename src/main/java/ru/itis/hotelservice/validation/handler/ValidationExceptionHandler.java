package ru.itis.hotelservice.validation.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.hotelservice.validation.dto.ValidationErrorDto;
import ru.itis.hotelservice.validation.dto.ValidationErrorsDto;

import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handleException(MethodArgumentNotValidException e) {
        List<ValidationErrorDto> errors = e.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    ValidationErrorDto errorDto = ValidationErrorDto.builder()
                            .message(error.getDefaultMessage())
                            .build();

                    if (error instanceof FieldError fieldError) {
                        errorDto.setField(fieldError.getField());

                        if (fieldError.getRejectedValue() != null) {
                            errorDto.setRejectedValue(fieldError.getRejectedValue().toString());
                        }
                    }

                    return errorDto;
                })
                .toList();

        return ResponseEntity.badRequest()
                .body(ValidationErrorsDto.builder()
                        .errors(errors)
                        .build());
    }
}
