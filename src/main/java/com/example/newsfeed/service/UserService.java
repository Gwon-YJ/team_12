package com.example.newsfeed.service;

import com.example.newsfeed.dto.LoginRequestDto;
import com.example.newsfeed.repository.UserRepository;
import com.example.newsfeed.utils.JwtUtil;
import com.example.newsfeed.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String login(LoginRequestDto request) {

        String userName = request.userName();
        String password = request.password();

        User user = userRepository.findByUsername(userName).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }

    // InitDat 저장용 으로 만든 메서드임
    public User save(User user) {
        return userRepository.save(user);
    }
}

