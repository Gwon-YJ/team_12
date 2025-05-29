package com.example.newsfeed.comment.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String author;
    private String content;
    private Long postId;
    private Long sessionId;
    private LocalDateTime createdAt;
}
