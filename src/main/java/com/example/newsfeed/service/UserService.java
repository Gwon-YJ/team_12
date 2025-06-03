package com.example.newsfeed.service;

import com.example.newsfeed.dto.LoginRequestDto;
import com.example.newsfeed.dto.SignupRequestDto;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.enums.UserRoleEnum;
import com.example.newsfeed.repository.UserRepository;
import com.example.newsfeed.utils.JwtUtil;
import com.example.newsfeed.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder; // Spring Security의 PasswordEncoder 사용
    private final UserRepository userRepository;

    public String login(LoginRequestDto request) {
        String email = request.email();
        String password = request.password();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

    public void signup(SignupRequestDto request) {
        String username = request.username();

        // 1. 중복 사용자 체크
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // 3. 사용자 엔티티 생성 (이메일 포함)
        User user = new User(
                username,
                encodedPassword,
                request.email(),
                UserRoleEnum.USER
        );

        // 4. 저장
        userRepository.save(user);

        // 5. 토큰 생성 없음! 그냥 리턴 안 하거나 void로 처리
    }


    // InitData 저장용 메서드
    public User save(User user) {
        return userRepository.save(user);
    }
}

