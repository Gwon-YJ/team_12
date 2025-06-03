package com.example.newsfeed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LikesResponseDto {

    private final Long postid;

    private final String username;

    private final String title;

    private final String content;

    private final Long likesCount;

    private final Long commentsCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;


    public LikesResponseDto(Long postid, String username, String title, String content,
                            Long likesCount, Long commentsCount,LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.postid = postid;
        this.username = username;
        this.title = title;
        this.content = content;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
