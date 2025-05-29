package com.example.newsfeed.post.controller;

import com.example.newsfeed.post.dto.PostRequestDto;
import com.example.newsfeed.post.dto.PostResponseDto;
import com.example.newsfeed.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
            HttpServletRequest servletRequest,
            @RequestBody PostRequestDto requestDto){
        return new ResponseEntity<>(postService.createPost(servletRequest, requestDto.getTitle(), requestDto.getContent()), HttpStatus.CREATED);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long postId,
            HttpServletRequest servletRequest,
            @RequestBody PostRequestDto requestDto){
        return new ResponseEntity<>(postService.updatePost(postId, servletRequest, requestDto.getTitle(), requestDto.getContent()), HttpStatus.OK);
    }

    // 특정 유저 게시글 리스트 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostResponseDto>> userPosts(
            @PathVariable Long userId){
        return new ResponseEntity<>(postService.userPosts(userId), HttpStatus.OK);
    }

    // 게시글 페이징 조회(팔로우한 사람의 게시물을 생성일 기준 내림차순)


    // 게시글 날짜 범위로 검색(수정일자 기준 내림차순)

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
