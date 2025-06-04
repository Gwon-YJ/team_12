package com.example.newsfeed.controller;

import com.example.newsfeed.config.JwtUtil;
import com.example.newsfeed.dto.LikesRequestDto;
import com.example.newsfeed.dto.LikesResponseDto;
import com.example.newsfeed.service.LikesService;
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
        String CustomId = jwtUtil.extractCustomId(token);

        return new ResponseEntity<>(likesService.signup(likesId,CustomId,likesRequestDto.getLikesCount()), HttpStatus.OK);
    }

    @DeleteMapping("/{likesId}")
    public ResponseEntity<Void> DeleteLikse(@PathVariable Long likesId,
                                       @RequestHeader("Authorization") String authorizationHeader){
        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거
        String CustomId = jwtUtil.extractCustomId(token);

        likesService.deleteLikes(likesId,CustomId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
