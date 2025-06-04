package com.example.newsfeed.dto;

import com.example.newsfeed.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostPageResponseDto {

    private final Long postId;

    private final String userName;

    private final String title;

    private final String content;

    private final Long likesCount;

    private final Long commentsCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;


    public PostPageResponseDto(Long postId, String userName, String title, String content, Long likesCount, Long commentsCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.postId = postId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static PostPageResponseDto toDto(Post post){

        return new PostPageResponseDto(
                post.getPostId(),
                post.getUser().getUserName(),
                post.getTitle(),
                post.getContent(),
                post.getLikesCount(),
                post.getCommentsCount(),
                post.getCreatedAt(),
                post.getModifiedAt()
        );
    }
}
