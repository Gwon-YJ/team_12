package com.example.newsfeed.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,updatable = true)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    public Post(){}

    public Post(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

}
