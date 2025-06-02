package com.example.newsfeed.controller;

import com.example.newsfeed.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandle(CustomException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ErrorMessage.of(ex.getMessage(), ex.getErrorCode()));
    }

    @AllArgsConstructor
    @Getter
    public static class ErrorMessage {
        String message;
        String errorCode;

        public static ErrorMessage of(String message, String errorCode) {
            return new ErrorMessage(message, errorCode);
        }
    }
}
