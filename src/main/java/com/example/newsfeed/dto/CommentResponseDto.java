package com.example.newsfeed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private Long userId;
    private Long postId;
    private String comment;

}
