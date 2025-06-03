package com.example.newsfeed.follow.dto;


import lombok.Getter;
import java.util.List;

@Getter
public class FollowResponseDto {
    private final List<FollowUserDto> users;

    public FollowResponseDto(List<FollowUserDto> users) {
        this.users = users;
    }
}