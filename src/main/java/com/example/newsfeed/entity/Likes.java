package com.example.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "likses")
public class Likes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long liksesId;


    @Column(nullable = false)
    @Setter
    private Long likeCount;

    public Likes(){}

    public Likes(Long likeCount){
        this.likeCount = likeCount;
    }

    public Likes(User user,Long likeCount){
        this.likeCount =likeCount;
        //this.post = post;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "postId")
    @Setter
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Setter
    private User user;
}
