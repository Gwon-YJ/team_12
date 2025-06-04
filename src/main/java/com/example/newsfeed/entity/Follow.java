package com.example.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor // 이거 최고네
@Table(name = "follows")
public class Follow extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;


    // 팔로우 거는 사람
    @ManyToOne
    @JoinColumn(name = "followerId", nullable = false)
    private User follower;

    // 팔로우 대상
    @ManyToOne
    @JoinColumn(name = "followingId", nullable = false)
    private User following;

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

}
