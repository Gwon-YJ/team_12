package com.example.newsfeed.entity;


import com.example.newsfeed.follow.entity.Follow;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false,updatable = true)
    private String userName;

    @Column(nullable = false,updatable = true)
    private String email;

    @Column(nullable = false)
    private String password;

    //팔로우
    @OneToMany(mappedBy = "following", fetch = FetchType.LAZY)
    private List<Follow> followingList;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
    private List<Follow> followerList;

}
