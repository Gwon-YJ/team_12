package com.example.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "follow")
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;


    @Column(nullable = false)
    private Long follows;

    public Follow(){}

    @ManyToOne
    @JoinColumn(name = "post_id")
    @Setter
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "follower_Id",nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_Id",nullable = false)
    private User following;

    public Follow(Long follows){
        this.follows =follows;
    }

    public Follow(User follows,User following){
        this.follower = follows;
        this.following = following;
    }
}
