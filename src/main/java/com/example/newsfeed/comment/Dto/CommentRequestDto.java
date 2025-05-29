package com.example.newsfeed.comment.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto {

    private final Long postId;
    private final String comment;


    public CommentRequestDto(Long postId, String comment) {
        this.postId = postId;
        this.comment = comment;
    }
}
