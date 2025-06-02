package com.example.newsfeed.dto;

public record SignupRequestDto (
    String username,
    String password,
    String email
) {
}

