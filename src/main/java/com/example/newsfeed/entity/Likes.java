package com.example.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "likse")
public class Likes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likseId;


    @Column(nullable = false)
    @Setter
    private Long likeCount;

    public Likes(){}

    public Likes(Long likeCount){
        this.likeCount = likeCount;
    }

    public Likes(User user,Long likeCount){
        this.likeCount =likeCount;
        //this.post = post;
        this.user = user;
    } // 여윽시 나야 ? 아니 그럼 말을 해주셔야죵

    @ManyToOne
    @JoinColumn(name = "postId")
    @Setter
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Setter
    private User user;
}
