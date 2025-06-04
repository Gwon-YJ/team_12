package com.example.newsfeed.controller;

import com.example.newsfeed.config.JwtUtil;
import com.example.newsfeed.dto.FollowResponseDto;
import com.example.newsfeed.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final JwtUtil jwtUtil;

    // 팔로우 생성
    @PostMapping("/{followingId}")
    public ResponseEntity<String> follow(@PathVariable Long followingId, @RequestHeader("Authorization") String authorizationHeader){

        // 현재 로그인한 사용자 정보를 조회한다
        String token = jwtUtil.extractBearerToken(authorizationHeader);

        return new ResponseEntity<>(followService.follow(followingId, token), HttpStatus.CREATED);
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
    @DeleteMapping("/{followId}")
    public ResponseEntity<String> unFollow(@PathVariable Long followId,@RequestHeader("Authorization") String authorizationHeader) {

        String token = jwtUtil.extractBearerToken(authorizationHeader);

        followService.unFollow(followId, token);
        return new ResponseEntity<>("팔로우 취소 완료", HttpStatus.OK);
    }
}
