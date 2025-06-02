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

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 팔로우 생성
    @PostMapping("/follow/{followingId}")
    public ResponseEntity<String> follow(@PathVariable Long followingId, HttpServletRequest servletRequest){

        // 현재 로그인한 사용자 정보
        HttpSession session = servletRequest.getSession(false);
        User follower = (User) session.getAttribute("sessionKey");

        return new ResponseEntity<>(followService.follow(followingId, follower), HttpStatus.CREATED);
    }

    // 유저 팔로우 조회(유저가 팔로우한)
    @GetMapping("/{userId}/following")
    public ResponseEntity<FollowResponseDto> followingList(@PathVariable Long userId){
        return new ResponseEntity<>(followService.followingList(userId), HttpStatus.OK);
    }

    // 유저 팔로워 조회(유저를 팔로우한)
    @GetMapping("/{userId}/follower")
    public ResponseEntity<FollowResponseDto> followerList(@PathVariable Long userId){
        return new ResponseEntity<>(followService.followerList(userId), HttpStatus.OK);
    }

    // 팔로우 취소
    @DeleteMapping("/unfollow/{followingId}")
    public ResponseEntity<String> unFollow(@PathVariable Long followingId, HttpServletRequest servletRequest){

        // 현재 로그인한 사용자 정보
        HttpSession session = servletRequest.getSession(false);
        User follower = (User) session.getAttribute("sessionKey");

        return new ResponseEntity<>(followService.unFollow(followingId, follower), HttpStatus.OK);
    }
}
