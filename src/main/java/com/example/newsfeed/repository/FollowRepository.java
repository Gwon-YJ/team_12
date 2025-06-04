package com.example.newsfeed.repository;

import com.example.newsfeed.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingUserIdAndFollowerUserId(Long followingId, Long userId); //캐리

}