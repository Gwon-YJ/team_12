package com.example.newsfeed.comment.controller;

import com.example.newsfeed.comment.Dto.CommentRequestDto;
import com.example.newsfeed.comment.Dto.CommentResponseDto;
import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        CommentResponseDto response = commentService.addComment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 댓글 조회
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long id) {
        CommentResponseDto response = commentService.getCommentById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody CommentRequestDto request) {
        Comment response = commentService.updateComment(id, request.getComment());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
