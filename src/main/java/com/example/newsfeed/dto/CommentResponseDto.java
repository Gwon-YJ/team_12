package com.example.newsfeed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long postId;

    private final String userName;

    private final String title;

    private final String content;

    private final String comments;

    private final Long likesCount;

    private final Long commentsCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 제가 시초에여
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;


    public CommentResponseDto(Long postId, String userName, String title, String content,String comments,
                            Long likesCount, Long commentsCount,LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.postId = postId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
