package org.example.newsfeed.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false,updatable = true)
    private String username;

    @Column(nullable = false,updatable = true)
    private String emdil;

    @Column(nullable = false)
    private int password;


    public User(){}

    public User(String username, String emdil, int password){
        this.username = username;
        this.emdil = emdil;
        this.password = password;
    }



}
