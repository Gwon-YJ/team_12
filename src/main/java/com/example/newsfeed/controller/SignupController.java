package com.example.newsfeed.controller;

import com.example.newsfeed.dto.SignupRequestDto;
import com.example.newsfeed.dto.SignupResponseDto;
import com.example.newsfeed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignupController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequestDto request) {
        userService.signup(request);
        return ResponseEntity.ok().build();  // 200 OK, 내용은 없음
    }
}