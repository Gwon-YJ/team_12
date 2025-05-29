package com.example.newsfeed.post.controller;

import com.example.newsfeed.post.dto.*;
import com.example.newsfeed.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

        HttpSession session = servletRequest.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        return new ResponseEntity<>(postService.createPost(userId, requestDto.getTitle(), requestDto.getContent()), HttpStatus.CREATED);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long postId,
            HttpServletRequest servletRequest,
            @RequestBody PostRequestDto requestDto){

        HttpSession session = servletRequest.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        return new ResponseEntity<>(postService.updatePost(postId, userId, requestDto.getTitle(), requestDto.getContent()), HttpStatus.OK);
    }

    // 특정 유저 게시글 리스트 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostResponseDto>> userPosts(
            @PathVariable Long userId){
        return new ResponseEntity<>(postService.userPosts(userId), HttpStatus.OK);
    }

    // 게시글 페이징 조회(게시물을 생성일 기준 내림차순) 추후에 팔로우한 사람들만 보이게 수정 예정
    @GetMapping
    public ResponseEntity<PostPageInfoResponseDto<PostPageResponseDto>> pagingPosts(
            @ModelAttribute PostPageRequestDto requestDto){
        return new ResponseEntity<>(postService.pagingPosts(requestDto.getPage(), requestDto.getSize()), HttpStatus.OK);
    }

    // 게시글 날짜 범위로 검색(수정일자 기준 내림차순)
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> searchPostsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") Timestamp startDate, @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") Timestamp endDate){
        return new ResponseEntity<>(postService.searchPostsByDateRange(startDate, endDate), HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, HttpServletRequest servletRequest){

        HttpSession session = servletRequest.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        postService.deletePost(postId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
