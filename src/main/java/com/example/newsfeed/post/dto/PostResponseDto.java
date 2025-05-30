package com.example.newsfeed.post.dto;

import com.example.newsfeed.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private final Long id;

    private final String username;

    private final String title;

    private final String content;

    private final Long likesCount;

    private final Long commentsCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    public PostResponseDto(Long id, String username, String title, String content, Long likesCount, Long commentsCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static PostResponseDto toDto(Post post){
        return new PostResponseDto(
                post.getPostId(),
                post.getUser().getUsername(),
                post.getTitle(),
                post.getContent(),
                post.getLikesCount(),
                post.getCommentsCount(),
                post.getCreatedAt(),
                post.getModifiedAt());
    }
}
