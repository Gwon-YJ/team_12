package com.example.newsfeed.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    @GetMapping("/get")
    public String getAdminInfo(HttpServletRequest request) {
        log.info("어드민 페이지 호출");
        return "어드민 페이지 리소스가 허가 되었습니다.";
    }
}
