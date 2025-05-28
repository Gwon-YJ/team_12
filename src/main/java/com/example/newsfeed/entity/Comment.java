package com.example.newsfeed.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    public Comment(){}

    public Comment(String comment){
        this.comment =comment;
    }

 // 테스트 커밋

    @ManyToOne
    @JoinColumn(name = "post_id")
    @Setter
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

}
