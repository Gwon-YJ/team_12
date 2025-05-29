package com.example.newsfeed.post.entity;


import com.example.newsfeed.entity.BaseEntity;
import com.example.newsfeed.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false,updatable = true)
    private String content;

    private Long likesCount = 0L;

    private Long commentsCount = 0L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(){

    }

    public Post(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

}
