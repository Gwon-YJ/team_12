package com.example.newsfeed.entity;

import com.example.newsfeed.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(name = "comment")
public class Comment extends BaseEntity {
    // 1. 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // 2. 생성자
    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }
}
