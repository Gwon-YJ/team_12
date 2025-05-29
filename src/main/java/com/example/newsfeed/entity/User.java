package com.example.newsfeed.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false,updatable = true)
    private String username;

    @Column(nullable = false,updatable = true)
    private String email;

    @Column(nullable = false)
    private int password;


    public User(){}

    public User(String username, String email, int password){
        this.username = username;
        this.email = email;
        this.password = password;
    }



}
