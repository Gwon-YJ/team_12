package com.example.newsfeed.service;

import com.example.newsfeed.dto.LoginRequestDto;
import com.example.newsfeed.config.PasswordEncoder;
import com.example.newsfeed.dto.UserRequestDto;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User login(UserRequestDto.LoginRequestDto loginRequestDto) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(loginRequestDto.getEmail()));
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("비밀번호나 이메일이 일치하지 않습니다.");
        }
        User user = optionalUser.get();
        String savePw = user.getPassword();
        String inputPw = loginRequestDto.getPassword();
        if(!passwordEncoder.matches(inputPw, savePw)) {
            throw new RuntimeException("비밀번호나 이메일이 일치하지 않습니다.");
        }
        return user;
    }
}
