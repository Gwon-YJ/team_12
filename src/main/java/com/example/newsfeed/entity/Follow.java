package com.example.newsfeed.entity;

import com.example.newsfeed.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "follows")
public class Follow extends BaseEntity{

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
    private Like like;

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
