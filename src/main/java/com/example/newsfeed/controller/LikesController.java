package com.example.newsfeed.controller;

import com.example.newsfeed.dto.LikesRequestDto;
import com.example.newsfeed.dto.LikesResponseDto;
import com.example.newsfeed.repository.LikesRepository;
import com.example.newsfeed.service.LikesService;
import com.example.newsfeed.service.PostService;
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

    @PostMapping("/{likesId}")
    public ResponseEntity<LikesResponseDto> signup(@PathVariable Long likesId,
                                                  HttpServletRequest servletRequest,
                                                  @RequestBody LikesRequestDto likesRequestDto){
        HttpSession session = servletRequest.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        return new ResponseEntity<>(likesService.signup(likesId,userId,likesRequestDto.getLikesCount()), HttpStatus.OK);
    }

    @DeleteMapping("/{likesId}")
    public ResponseEntity<Void> signup(@PathVariable Long likesId,
                                                   HttpServletRequest servletRequest){
        HttpSession session = servletRequest.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        likesService.deleteLikes(likesId,userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
