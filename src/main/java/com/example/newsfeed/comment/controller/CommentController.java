package com.example.newsfeed.comment.controller;


import com.example.newsfeed.comment.Dto.CommentRequestDto;
import com.example.newsfeed.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Comments")
public class CommentController {
    // 1. 속성
    private final CommentService commentService;

    // 2. 생성자
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 3. 기능
    // 댓글 생성
    @PostMapping
    public ResponseEntity<String> Comment(@RequestBody CommentRequestDto request) {
        commentService.addCommit(request.getPostId(), request.getComment());
        return ResponseEntity.ok("댓글이 성공적으로 등록되었습니다.");
    }
}
