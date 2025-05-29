package com.example.newsfeed.controller;

import com.example.newsfeed.dto.LoginRequestDto;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping()
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest) {
        User user = loginService.login(loginRequestDto);
        //로그인 후 세션 없으면 생성, 있으면 세션 반환
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("sessionKey", user.getUserId());
        return new ResponseEntity<>("로그인에 성공했습니다.", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession httpSession, HttpServletResponse httpServletResponse){
        httpSession.invalidate();
        return new ResponseEntity<>("로그아웃에 성공했습니다.", HttpStatus.OK);
    }
}