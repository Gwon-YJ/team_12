package com.example.newsfeed.entity;


import com.example.newsfeed.config.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false,unique = true, updatable = false)
    private String customId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    //팔로우
    @OneToMany(mappedBy = "following", fetch = FetchType.LAZY)
    private List<Follow> followingList;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY) //세션이 그리운 순간 
    private List<Follow> followerList;

    public User(String userName, String password, String email, UserRoleEnum role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

}