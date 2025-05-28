package com.example.newsfeed.comment.entity;

import com.example.newsfeed.comment.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; // 댓글 내용

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
