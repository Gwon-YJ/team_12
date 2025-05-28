package com.example.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "follow")
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;


    @Column(nullable = false)
    private Long follows;

    public Follow(){}

    public Follow(Long follows){
        this.follows =follows;
    }

    @ManyToOne
    @JoinColumn(name = "like_id")
    @Setter
    private Likes like;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    @Setter
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @Setter
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

}
