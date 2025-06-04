package com.example.newsfeed.controller;

import com.example.newsfeed.config.JwtUtil;
import com.example.newsfeed.dto.CommentRequestDto;
import com.example.newsfeed.dto.CommentResponseDto;

import com.example.newsfeed.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final JwtUtil jwtUtil;

    // 댓글 생성
    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponseDto> saveComment(@PathVariable Long postId,@RequestBody CommentRequestDto request,
                                                          @RequestHeader("Authorization") String authorizationHeader) {
        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거 요거 그냥 쓰져

        CommentResponseDto response = commentService.saveComment(postId,token,request.getComment());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // 댓글 삭제
    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId,@PathVariable Long commentId,
     @RequestHeader("Authorization") String authorizationHeader) {
        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거

        commentService.deleteComment(postId,commentId,token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}