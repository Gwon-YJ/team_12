package com.example.newsfeed.post.controller;

import com.example.newsfeed.post.JwtUtil;
import com.example.newsfeed.post.dto.PostPageInfoResponseDto;
import com.example.newsfeed.post.dto.PostPageRequestDto;
import com.example.newsfeed.post.dto.PostRequestDto;
import com.example.newsfeed.post.dto.PostResponseDto;
import com.example.newsfeed.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final JwtUtil jwtUtil;


    // 게시글 생성
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody PostRequestDto requestDto) {
        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거
        String customId = jwtUtil.extractCustomId(token);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.createPost(customId, requestDto.getTitle(), requestDto.getContent()));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long postId,
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody PostRequestDto requestDto) {

        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거
        String customId = jwtUtil.extractCustomId(token);

        PostResponseDto response = postService.updatePost(
                postId,
                customId,
                requestDto.getTitle(),
                requestDto.getContent());

        return ResponseEntity.ok(response);
    }

    // 특정 유저 게시글 리스트 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostResponseDto>> userPosts(
            @PathVariable Long userId){
        return new ResponseEntity<>(postService.userPosts(userId), HttpStatus.OK);
    }

    // 게시글 페이징 조회(게시물을 생성일 기준 내림차순) 추후에 팔로우한 사람들만 보이게 수정 예정
    @GetMapping("/paging")
    public ResponseEntity<PostPageInfoResponseDto> pagingPosts(
            @Valid @ModelAttribute PostPageRequestDto requestDto){
        return new ResponseEntity<>(postService.pagingPosts(requestDto.getPage(), requestDto.getSize()), HttpStatus.OK);
    }

    // 게시글 날짜 범위로 검색(수정일자 기준 내림차순)
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> searchPostsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDateTime startDate, @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDateTime endDate){
        return new ResponseEntity<>(postService.searchPostsByDateRange(startDate, endDate), HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @RequestHeader("Authorization") String authorizationHeader){

        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거
        String customId = jwtUtil.extractCustomId(token);

        postService.deletePost(postId, customId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}