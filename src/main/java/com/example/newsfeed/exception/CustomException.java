package com.example.newsfeed.exception;

import lombok.Getter;

@Getter
public class CustomException  extends RuntimeException{
    private final ErrorType errorType;
    public CustomException(ErrorType errorType){
        this.errorType = errorType;
    }
}
