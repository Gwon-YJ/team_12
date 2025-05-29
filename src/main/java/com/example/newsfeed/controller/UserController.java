package com.example.newsfeed.controller;

import com.example.newsfeed.dto.UserRequestDto;
import com.example.newsfeed.dto.UserResponseDto;
import com.example.newsfeed.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
        return new ResponseEntity<>("수정 성공", HttpStatus.OK);
    }
}
