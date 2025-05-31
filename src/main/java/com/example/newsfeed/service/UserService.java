package com.example.newsfeed.service;

import com.example.newsfeed.config.PasswordEncoder;
import com.example.newsfeed.dto.UserResponseDto;
import com.example.newsfeed.dto.UserRequestDto;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorType;
import com.example.newsfeed.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signUp(UserRequestDto.SignUp userRequestDto){
        User user = new User();
        user.setUserName(userRequestDto.getUserName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto(user);

        return userResponseDto;
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

    public UserResponseDto updateUser(Long userId, UserRequestDto.UpdateUser userRequestDto) {
        User user = isUserEmpty(userId);
        if(!passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword()))
            throw new CustomException(ErrorType.PASSWORD_MISMATCH);
        if(userRequestDto.getUserName() != null)
            user.setUserName(userRequestDto.getUserName());
        if(userRequestDto.getEmail() != null)
            user.setEmail(userRequestDto.getEmail());
        userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto(user);

        return userResponseDto;
    }

    public void updateUserPw(Long userId, UserRequestDto.UpdatePw userRequestDto) {
        User user = isUserEmpty(userId);
        if(!passwordEncoder.matches(userRequestDto.getSavePassword(), user.getPassword()))
            throw new CustomException(ErrorType.PASSWORD_MISMATCH);
        user.setPassword(passwordEncoder.encode(userRequestDto.getChangePassword()));
        userRepository.save(user);
    }

    public void deleteUser(Long userId, UserRequestDto.DeleteUser userRequestDto) {
        User user = isUserEmpty(userId);
        if(!passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword()))
            throw new CustomException(ErrorType.PASSWORD_MISMATCH);
        userRepository.deleteById(userId);
    }

    private User isUserEmpty(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty())
            throw new CustomException(ErrorType.ENTITY_NOT_FOUND);
        User user = optionalUser.get();

        return user;
    }
}