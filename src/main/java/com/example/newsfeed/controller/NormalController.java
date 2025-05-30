package com.example.newsfeed.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/normal")
@RequiredArgsConstructor
@Slf4j
public class NormalController {

    @GetMapping("/get")
    public String getUserInfo(HttpServletRequest request) {
        log.info("일반적으로 모두 사용할 수 있는 페이지 호출");
        return "일반 페이지 리소스가 허가 되었습니다.";
    }
}
