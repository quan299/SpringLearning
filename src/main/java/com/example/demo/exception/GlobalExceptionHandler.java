package com.example.demo.exception;

import com.example.demo.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException exception){

        ApiResponse<Void> response = new ApiResponse<>();

        response.setCode(1001);
        response.setMessage(exception.getMessage());
        response.setResult(null);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }

}