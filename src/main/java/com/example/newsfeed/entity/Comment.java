package com.example.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long commentsCount;

    public Comment(String comment,Long commentsCount){
        this.comment = comment;
        this.commentsCount = commentsCount;
    }

    @ManyToOne
    @JoinColumn(name = "postId") // 해고 당했어여 들켰다요
    @Setter
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Setter
    private User user;

}
