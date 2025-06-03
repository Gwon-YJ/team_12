package com.example.newsfeed.follow.controller;

import com.example.newsfeed.entity.User;
import com.example.newsfeed.follow.dto.FollowResponseDto;
import com.example.newsfeed.follow.service.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;



@RestController
@RequestMapping
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 팔로우 생성
    @PostMapping("/follow/{targetUserId}")
    public ResponseEntity<?> follow(@PathVariable Long targetUserId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User follower = (User) authentication.getPrincipal();

        return new ResponseEntity<>(followService.follow(targetUserId, follower), HttpStatus.CREATED);
    }


    // 유저 팔로우 조회(유저가 팔로우한)
    @GetMapping("/follow/following")
    public ResponseEntity<?> getMyFollowing() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        return new ResponseEntity<>(followService.followingList(user.getUserId()), HttpStatus.OK);
    }


    // 유저 팔로워 조회(유저를 팔로우한)
    @GetMapping("/follow/followers")
    public ResponseEntity<?> getMyFollowers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        return new ResponseEntity<>(followService.followerList(user.getUserId()), HttpStatus.OK);
    }


    // 팔로우 취소
    @DeleteMapping("/follow/{followId}")
    public ResponseEntity<String> unFollow(@PathVariable Long followId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User follower = (User) authentication.getPrincipal();  // 현재 로그인한 사용자

        followService.unFollow(followId, follower);
        return new ResponseEntity<>("팔로우 취소 완료", HttpStatus.OK);
    }

}
