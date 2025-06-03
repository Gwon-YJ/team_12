package com.example.newsfeed.controller;

import com.example.newsfeed.dto.LoginRequestDto;
import com.example.newsfeed.dto.UserRequestDto;
import com.example.newsfeed.dto.UserResponseDto;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.service.LoginService;
import com.example.newsfeed.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    @PostMapping()
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto.SignUp userRequestDto){
        UserResponseDto resultDto = userService.signUp(userRequestDto);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> findUserList(@Valid @RequestBody UserRequestDto.FindByName userRequestDto){
        List<UserResponseDto> resultDtoList = userService.findUserList(userRequestDto);
        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long userId){
        UserResponseDto resultDto = userService.findUser(userId);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDto.UpdateUser userRequestDto, HttpServletRequest httpServletRequest){
        Long sessionId = (Long)httpServletRequest.getSession().getAttribute("sessionKey");
        UserResponseDto resultDto = userService.updateUser(userId, sessionId, userRequestDto);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserPw(@PathVariable Long userId, @Valid @RequestBody UserRequestDto.UpdatePw userRequestDto, HttpServletRequest httpServletRequest){
        Long sessionId = (Long)httpServletRequest.getSession().getAttribute("sessionKey");
        userService.updateUserPw(userId, sessionId, userRequestDto);
        return new ResponseEntity<>("수정 성공", HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId, @RequestBody UserRequestDto.DeleteUser userRequestDto, HttpServletRequest httpServletRequest){
        Long sessionId = (Long)httpServletRequest.getSession().getAttribute("sessionKey");
        userService.deleteUser(userId, sessionId, userRequestDto);
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserRequestDto.LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest) {
        User user = loginService.login(loginRequestDto);
        //로그인 후 세션 없으면 생성, 있으면 세션 반환
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("sessionKey", user.getUserId());
        return new ResponseEntity<>("로그인에 성공했습니다.", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession httpSession){
        httpSession.invalidate();
        return new ResponseEntity<>("로그아웃에 성공했습니다.", HttpStatus.OK);
    }
}
