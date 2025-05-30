package com.example.newsfeed.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleException(CustomException customeException){
        ErrorType errorType = customeException.getErrorType();
        return new ResponseEntity<>(errorType.getErrorMessage(), errorType.getHttpStatus());
    }

    /*
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundHandler(){
        return new ResponseEntity<>(ErrorType.ENTITY_NOT_FOUND.getErrorMessage(), ErrorType.ENTITY_NOT_FOUND.getHttpStatus());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValid(){
        return new ResponseEntity<>(ErrorType.METHOD_ARGUMENT_NOT_VALID.getErrorMessage(), ErrorType.METHOD_ARGUMENT_NOT_VALID.getHttpStatus());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDenied(){
        return new ResponseEntity<>(ErrorType.ACCESS_DENIED.getErrorMessage(), ErrorType.ACCESS_DENIED.getHttpStatus());
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authentication(){
        return new ResponseEntity<>(ErrorType.LOGIN_AUTHENTICATION.getErrorMessage(), ErrorType.LOGIN_AUTHENTICATION.getHttpStatus());
    }
    @ExceptionHandler(PassworMismatchException.class)
    public ResponseEntity<String> passwordMismatch(){
        return new ResponseEntity<>(ErrorType.PASSWORD_MISMATCH.getErrorMessage(), ErrorType.PASSWORD_MISMATCH.getHttpStatus());
    }

     */


}
