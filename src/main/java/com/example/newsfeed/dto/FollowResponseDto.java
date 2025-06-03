package com.example.newsfeed.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FollowResponseDto {

    private final List<String> userName;


    public FollowResponseDto(List<String> userName) {
        this.userName = userName;
    }
}
