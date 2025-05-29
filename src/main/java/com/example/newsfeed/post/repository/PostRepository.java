package com.example.newsfeed.post.repository;

import com.example.newsfeed.entity.User;
import com.example.newsfeed.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);

    Long user(User user);
}
