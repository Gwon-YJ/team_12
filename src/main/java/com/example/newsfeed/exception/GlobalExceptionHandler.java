package com.example.newsfeed.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleException(CustomException customeException) {
        ErrorType errorType = customeException.getErrorType();
        return new ResponseEntity<>(errorType.getErrorMessage(), errorType.getHttpStatus());
    }

}
