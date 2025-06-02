package com.example.newsfeed.follow.entity;

import com.example.newsfeed.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.newsfeed.entity.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "follow")
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 팔로우 거는 사람
    @ManyToOne
    @JoinColumn(name = "follower_Id", nullable = false)
    private User follower;

    // 팔로우 대상
    @ManyToOne
    @JoinColumn(name = "following_Id", nullable = false)
    private User following;

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }
}
