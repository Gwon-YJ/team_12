package com.example.newsfeed.repository;

import com.example.newsfeed.entity.Comment;
import com.example.newsfeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByPost(Post post);

}