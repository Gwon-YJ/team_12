package com.example.newsfeed.controller;

import com.example.newsfeed.dto.LikesRequestDto;
import com.example.newsfeed.dto.LikesResponseDto;
import com.example.newsfeed.repository.LikesRepository;
import com.example.newsfeed.service.LikesService;
import com.example.newsfeed.service.PostService;
import com.example.newsfeed.utils.JwtUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class LikesController {

    private final LikesService likesService;
    private final JwtUtil jwtUtil;

    @PostMapping("/{likesId}")
    public ResponseEntity<LikesResponseDto> signup(@PathVariable Long likesId,
                                                   @RequestHeader("Authorization") String authorizationHeader,
                                                   @RequestBody LikesRequestDto likesRequestDto){
        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거
        String username = jwtUtil.extractUsername(token);

        return new ResponseEntity<>(likesService.signup(likesId,username,likesRequestDto.getLikesCount()), HttpStatus.OK);
    }

    @DeleteMapping("/{likesId}")
    public ResponseEntity<Void> signup(@PathVariable Long likesId,
                                       @RequestHeader("Authorization") String authorizationHeader){
        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거
        String username = jwtUtil.extractUsername(token);

        likesService.deleteLikes(likesId,username);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
