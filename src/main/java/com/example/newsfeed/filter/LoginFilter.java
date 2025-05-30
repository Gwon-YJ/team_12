package com.example.newsfeed.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] FILTER_PASS_URI = {"/login", "/users"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        log.info("로그인 필터 로직 실행");

        //패턴에 불일치 하면 필터 로직(인증 로직) 실행
        if(!isFilterPassUri(requestURI)){
            //세션이 없으면 null 반환
            HttpSession httpSession = httpServletRequest.getSession(false);
            if(httpSession == null || httpSession.getAttribute("sessionKey") == null) {
                log.warn("세션이 없거나 세션 키가 유효하지 않습니다.");
                throw new RuntimeException("로그인이 필요합니다.");
            }
        }
        log.info("필터 통과!.");
        //다음 필터가 없으면 서블릿과 컨트롤러가 요청되게 만들어주고 다음 필터가 있으면 다음 필터를 호출한다.
        filterChain.doFilter(servletRequest, servletResponse);
    }


    private boolean isFilterPassUri(String requestURI){
        //Pattern에 일치하면 true 반환
        return PatternMatchUtils.simpleMatch(FILTER_PASS_URI, requestURI);
    }
}