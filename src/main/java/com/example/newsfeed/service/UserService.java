package com.example.newsfeed.service;

import com.example.newsfeed.config.JwtUtil;
import com.example.newsfeed.config.PasswordEncoder;
import com.example.newsfeed.config.UserRoleEnum;
import com.example.newsfeed.dto.UserResponseDto;
import com.example.newsfeed.dto.UserRequestDto;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorType;
import com.example.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.Error;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(UserRequestDto.Login userRequestDto) {
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();


        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorType.PASSWORD_EMAIL_MISMATCH)
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorType.PASSWORD_EMAIL_MISMATCH);
        }

        return jwtUtil.generateToken(user.getCustomId(), user.getRole());
    }

    public void signUp(UserRequestDto.SignUp userRequestDto){
        //해당 이메일의 사용자 존재 확인 및 존재 시 예외처리
        Optional<User> optionalUser = userRepository.findByEmail(userRequestDto.getEmail());
        if(optionalUser.isPresent())
            throw new CustomException(ErrorType.RESOURCE_ALREADY_EXIST);

        User user = new User();
        user.setUserName(userRequestDto.getUserName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setCustomId(userRequestDto.getCustomId());
        user.setRole(UserRoleEnum.USER);
        userRepository.save(user);
    }

    public List<UserResponseDto> findUserList(UserRequestDto.FindByName userRequestDto){
        List<User> userList = userRepository.findAllByUserNameOrderByModifiedAtDesc(userRequestDto.getUserName());
        List<UserResponseDto> userDtoList = userList.stream().map(user -> new UserResponseDto(user)).collect(Collectors.toList());
        //없으면 빈 리스트를 반환합니다.
        return userDtoList;
    }

    public UserResponseDto findUser(Long userId) {
        User user = isUserEmpty(userId);
        UserResponseDto userResponseDto = new UserResponseDto(user);

        return userResponseDto;
    }


    public UserResponseDto updateUser(Long userId, String authorizationHeader, UserRequestDto.UpdateUser userRequestDto) {
        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거
        //토큰 만료 확인
        if(!jwtUtil.validateToken(token))
            throw new CustomException(ErrorType.TOKEN_EXPIRED);

        User user = isUserEmpty(userId);

        //접근 권한 확인
        String customId = jwtUtil.extractCustomId(token);
        if(!customId.equals(user.getCustomId()))
            throw new CustomException(ErrorType.ACCESS_DENIED);

        // 비밀번호 검증
        if(!passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword()))
            throw new CustomException(ErrorType.PASSWORD_MISMATCH);

        //사용자 정보 변경
        if(userRequestDto.getUserName() != null)
            user.setUserName(userRequestDto.getUserName());
        if(userRequestDto.getEmail() != null)
            user.setEmail(userRequestDto.getEmail());
        userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto(user);

        return userResponseDto;
    }

    public void updateUserPw(Long userId, String authorizationHeader, UserRequestDto.UpdatePw userRequestDto) {
        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거
        //토큰 만료 확인
        if(!jwtUtil.validateToken(token))
            throw new CustomException(ErrorType.TOKEN_EXPIRED);

        User user = isUserEmpty(userId);

        //접근 권한 확인
        String customId = jwtUtil.extractCustomId(token);
        if(!customId.equals(user.getCustomId()))
            throw new CustomException(ErrorType.ACCESS_DENIED);

        // 비밀번호 검증
        if(!passwordEncoder.matches(userRequestDto.getSavePassword(), user.getPassword()))
            throw new CustomException(ErrorType.PASSWORD_MISMATCH);

        // 비밀번호 변경
        user.setPassword(passwordEncoder.encode(userRequestDto.getChangePassword()));
        userRepository.save(user);
    }

    public void deleteUser(Long userId, String authorizationHeader, UserRequestDto.DeleteUser userRequestDto) {
        String token = jwtUtil.extractBearerToken(authorizationHeader);  // "Bearer " 제거
        //토큰 만료 확인
        if(!jwtUtil.validateToken(token))
            throw new CustomException(ErrorType.TOKEN_EXPIRED);

        User user = isUserEmpty(userId);

        //접근 권한 확인
        String customId = jwtUtil.extractCustomId(token);
        if(!customId.equals(user.getCustomId()))
            throw new CustomException(ErrorType.ACCESS_DENIED);

        // 비밀번호 검증
        if(!passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword()))
            throw new CustomException(ErrorType.PASSWORD_MISMATCH);

        // 삭제
        userRepository.deleteById(userId);
    }

    private User isUserEmpty(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty())
            throw new CustomException(ErrorType.ENTITY_NOT_FOUND);
        return optionalUser.get();
    }

}