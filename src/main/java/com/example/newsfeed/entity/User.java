package com.example.newsfeed.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

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

    public User(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @OneToMany(mappedBy = "following",fetch = FetchType.LAZY)
    private List<Follow> followsingList;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
    public List<Follow> followsrList;


}
