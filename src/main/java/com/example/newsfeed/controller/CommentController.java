package com.example.newsfeed.controller;

import com.example.newsfeed.dto.CommentRequestDto;
import com.example.newsfeed.dto.CommentResponseDto;

import com.example.newsfeed.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment(@RequestBody CommentRequestDto request) {
        CommentResponseDto response = commentService.saveComment(request.getComment());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 댓글 조회
    @GetMapping("/{id}")
    public ResponseEntity<List<CommentResponseDto>> findById(@PathVariable Long id) {
        List<CommentResponseDto> response = commentService.findComment(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long id, @RequestBody CommentRequestDto request) {
        CommentResponseDto response = commentService.updateComment(id, request.getComment());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
