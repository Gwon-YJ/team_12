package com.example.newsfeed.dto;

import lombok.Getter;

@Getter
public class LikesRequestDto {

    private final Long likesCount;

    public LikesRequestDto(Long likesCount){
        this.likesCount = likesCount;
    }

}