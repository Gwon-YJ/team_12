package com.example.newsfeed.follow.repository;

import com.example.newsfeed.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingIdAndFollowerId(Long followingId, Long userId);
}
