package com.example.newsfeed.repository;

import com.example.newsfeed.entity.Follow;
import com.example.newsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingUserIdAndFollowerUserId(Long followingId, Long userId); //캐리

    //  중복 팔로우 체크 및 삭제용
    Optional<Follow> findByFollowing_UserIdAndFollower_UserId(Long followingId, Long userId);

    //  내가 팔로우한 유저들 (-> followingList)
    List<Follow> findAllByFollower(User follower);

    // 나를 팔로우한 유저들 (-> followerList)
    List<Follow> findAllByFollowing(User following);
}