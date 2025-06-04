package com.example.newsfeed.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j(topic = "JwtFilter")
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();
        String username = null;
        String jwt = null;


        String authorizationHeader = httpRequest.getHeader("Authorization");


        // 처음 로그인 하는 거야? 그럼 JWT 토큰이 없을 것이니 토큰 먼저 발급 받아!
        if(requestURI.equals("/users/login") || requestURI.equals("/users/signup") || requestURI.equals("/users/{userId}")) {
            chain.doFilter(request,response);
            return;
        }

        // 게시글 조회 필터 처리
        String path = httpRequest.getRequestURI();

        if (path.startsWith("/posts") && httpRequest.getMethod().equals("GET")) {
            chain.doFilter(request, response); // 인증 없이 통과
            return;
        }

        // 로그인 하는게 아니네? 그럼 JWT 토큰 있어?
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.info("JWT 토큰이 필요 합니다.");
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰이 필요 합니다.");
            return;
        }


        jwt = authorizationHeader.substring(7);

        // Secret Key 는 내가 만든게 맞는지 검증 만료 기간 지났는지 검증
        if (!jwtUtil.validateToken(jwt)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("{\"error\": \"Unauthorized\"}");
        }

        // 전용 API가 아닌 일반 API의 경우
        chain.doFilter(request, response);
    }
}