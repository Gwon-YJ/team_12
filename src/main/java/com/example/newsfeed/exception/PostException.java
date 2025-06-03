//package com.example.newsfeed.exception;
//
//import org.springframework.http.HttpStatus;
//
//public class PostException extends CustomException {
//
//    public PostException(String errorCode, String message, HttpStatus status) {
//        super(errorCode, message, status);
//    }
//
//    public static class PostNotFoundException extends PostException {
//        public PostNotFoundException(){
//            super("에러 코드", "해당 게시물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
//        }
//    }
//}
