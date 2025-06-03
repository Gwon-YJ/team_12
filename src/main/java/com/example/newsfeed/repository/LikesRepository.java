package com.example.newsfeed.repository;

import com.example.newsfeed.entity.Likes;
import com.example.newsfeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Likes findByLikseId(Long id);
}
