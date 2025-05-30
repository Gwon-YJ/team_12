package com.example.newsfeed.controller;


import com.example.newsfeed.dto.LoginRequestDto;
import com.example.newsfeed.dto.LoginResponseDto;
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
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {

        String token  = userService.login(request);

        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
