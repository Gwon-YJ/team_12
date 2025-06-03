package com.example.newsfeed.dto;

import com.example.newsfeed.entity.Likes;
import lombok.Getter;

@Getter
public class LikesRequestDto {

    private final Long likesCount;

    public LikesRequestDto(Long likesCount){
        this.likesCount = likesCount;
    }

}