package com.example.newsfeed.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(JwtUtil jwtUtil) {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new JwtFilter(jwtUtil));
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}