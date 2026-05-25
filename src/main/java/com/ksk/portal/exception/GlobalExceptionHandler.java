package com.ksk.portal.exception;

import com.ksk.portal.dto.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex
    ) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        404,
                        "NOT_FOUND",
                        ex.getMessage()
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(LicenseUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleLicenseUnavailable(
            LicenseUnavailableException ex
    ) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        400,
                        "LICENSE_UNAVAILABLE",
                        ex.getMessage()
                );

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex
    ) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        400,
                        "VALIDATION_ERROR",
                        "Invalid request body"
                );

        return ResponseEntity
                .badRequest()
                .body(response);
    }
}