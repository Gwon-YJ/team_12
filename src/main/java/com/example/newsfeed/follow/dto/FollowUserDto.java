package com.example.newsfeed.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowUserDto {
    private Long userId;
    private String userName;

}
