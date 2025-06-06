package com.example.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false,updatable = true)
    private String content;

    @Setter
    private Long likesCount = 0L;

    @Setter
    private Long commentsCount = 0L;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Post(){

    }

    public Post(String title, String content, User user,Long likesCount,Long commentsCount){
        this.title = title;
        this.content = content;
        this.user = user;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
    }

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}