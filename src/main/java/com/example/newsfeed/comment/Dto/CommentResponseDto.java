package com.example.newsfeed.comment.Dto;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long postId;
    private final String comment;

    public CommentResponseDto(Long postId, String comment) {
        this.postId = postId;
        this.comment = comment;
    }
}
