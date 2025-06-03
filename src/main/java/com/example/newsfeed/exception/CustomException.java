package com.example.newsfeed.exception;

import lombok.Getter;

@Getter
//@AllArgsConstructor
public class CustomException extends RuntimeException{
//    private String errorCode;
//    private String message;
//    private HttpStatus status;

    private final ErrorType errorType;
    public CustomException(ErrorType errorType){
        this.errorType = errorType;
    }

}
