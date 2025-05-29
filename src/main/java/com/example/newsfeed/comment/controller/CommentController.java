package com.example.newsfeed.comment.controller;


import com.example.newsfeed.comment.Dto.CommentRequestDto;
import com.example.newsfeed.comment.Dto.CommentResponseDto;
import com.example.newsfeed.comment.service.CommentService;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String>saveComment(@RequestBody CommentRequestDto request) {
        ResponseDto postResponseDto =
                postservice.SaveUp(
                        request.getPostId(),
                        request.getComment()
                );
   return new ResponseEntity<>(postResponseDto, HttpStatus.CREATED);
    }


    // 댓글 조회
    @GetMapping("/{id}")
    public ResponseEntity<String>findById(@PathVariable Long id) {

        PostResponDto postResponDto = postService.findById(id);

        return new ResponseEntity<>(postResponDto, HttpStatus.OK);
    }


    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<String>update(
            @PathVariable Long id,
            @RequestBody UpdatePostDto updatePostDto
    ) {
        PostResponseDto postResponseDto = postService.updatePost(id, updatePostDto.getId(), updatePostDto.gitComment());

        return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        postService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
