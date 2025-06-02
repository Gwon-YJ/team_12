package com.example.newsfeed.follow.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FollowResponseDto {

    private final List<String> userName;


    public FollowResponseDto(List<String> userName) {
        this.userName = userName;
    }
}
