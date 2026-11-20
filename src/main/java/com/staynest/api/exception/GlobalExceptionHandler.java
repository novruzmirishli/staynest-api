
package com.staynest.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<Map<String, String>> details =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> Map.of(
                                "field", fieldError.getField(),
                                "message", fieldError.getDefaultMessage() == null
                                        ? "Invalid value"
                                        : fieldError.getDefaultMessage()
                        ))
                        .toList();

        Map<String, Object> error = new LinkedHashMap<>();
        error.put("code", "VALIDATION_ERROR");
        error.put("message", "The request contains invalid or missing fields.");
        error.put("details", details);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", false);
        response.put("error", error);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
