package com.example.newsfeed.repository;

import com.example.newsfeed.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Likes findByLiksesId(Long id);
}
