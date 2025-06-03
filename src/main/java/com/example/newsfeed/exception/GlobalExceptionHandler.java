package com.example.newsfeed.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<?> customExceptionHandle(CustomException ex) {
//        return ResponseEntity
//                .status(ex.getStatus())
//                .body(ErrorMessage.of(ex.getMessage(), ex.getErrorCode()));
//    }
//
//    @AllArgsConstructor
//    @Getter
//    public static class ErrorMessage {
//        String message;
//        String errorCode;
//
//        public static ErrorMessage of(String message, String errorCode) {
//            return new ErrorMessage(message, errorCode);
//        }
//    }
 @ExceptionHandler(CustomException.class)
 public ResponseEntity<String> handleException(CustomException customeException){
     ErrorType errorType = customeException.getErrorType();
    return new ResponseEntity<>(errorType.getErrorMessage(), errorType.getHttpStatus());
  }


}
