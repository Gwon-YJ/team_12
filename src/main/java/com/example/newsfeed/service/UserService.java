package com.example.newsfeed.service;

import com.example.newsfeed.config.PasswordEncoder;
import com.example.newsfeed.dto.UserResponseDto;
import com.example.newsfeed.dto.UserRequestDto;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.repository.UserRepository;
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
        //api 명세에 보면 url 경로에 userId를 받기 때문에 request param 아니고 path variable인 것 같습니다.
        Optional<User> optionalUser = userRepository.findById(userId);
        //isEmpty()에 대한 커스텀 예외처리 할 예정
        User user = optionalUser.get();
        UserResponseDto userResponseDto = new UserResponseDto(user);

        return userResponseDto;
    }

    public UserResponseDto updateUser(Long userId, Long sessionId, UserRequestDto.UpdateUser userRequestDto) {
        if(!userId.equals(sessionId))
            throw new RuntimeException("접근 권한이 없습니다.");
        Optional<User> optionalUser = userRepository.findById(userId);
        //isEmpty()에 대한 커스텀 예외처리 할 예정
        User user = optionalUser.get();
        //비밀번호 틀릴 시 예외처리 할 예정
        //현재 예외처리를 하지 않아서 비밀번호가 틀려도 변경만 안 될 뿐 예외가 발생하지 않습니다.
        if(passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword())){
            if(userRequestDto.getUserName() != null)
                user.setUserName(userRequestDto.getUserName());
            if(userRequestDto.getEmail() != null)
                user.setEmail(userRequestDto.getEmail());
            userRepository.save(user);
        }
        UserResponseDto userResponseDto = new UserResponseDto(user);

        return userResponseDto;
    }

    public void updateUserPw(Long userId, Long sessionId, UserRequestDto.UpdatePw userRequestDto) {
        if(!userId.equals(sessionId))
            throw new RuntimeException("접근 권한이 없습니다.");
        Optional<User> optionalUser = userRepository.findById(userId);
        //isEmpty()에 대한 커스텀 예외처리 할 예정
        User user = optionalUser.get();
        //저장 비밀번호 불일치 시 예외처리 예정
        //현재 예외처리를 하지 않아서 비밀번호가 틀려도 변경만 안 될 뿐 예외가 발생하지 않습니다.
        if(passwordEncoder.matches(userRequestDto.getSavePassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userRequestDto.getChangePassword()));
            userRepository.save(user);
        }
    }

    public void deleteUser(Long userId, Long sessionId, UserRequestDto.DeleteUser userRequestDto) {
        if(!userId.equals(sessionId))
            throw new RuntimeException("접근 권한이 없습니다.");
        Optional<User> optionalUser = userRepository.findById(userId);
        //isEmpty()에 대한 커스텀 예외처리 할 예정
        User user = optionalUser.get();
        System.out.println(userRequestDto.getPassword());
        System.out.println(user.getPassword());
        //비밀번호 틀릴 시 예외처리 할 예정
        //현재 예외처리를 하지 않아서 비밀번호가 틀려도 변경만 안 될 뿐 예외가 발생하지 않습니다.
        if(passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword())){
            userRepository.deleteById(userId);
        }
    }
}
