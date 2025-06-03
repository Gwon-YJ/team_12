package com.example.newsfeed.controller;

import com.example.newsfeed.config.JwtUtil;
import com.example.newsfeed.dto.LoginResponseDto;
import com.example.newsfeed.dto.UserRequestDto;
import com.example.newsfeed.dto.UserResponseDto;
import com.example.newsfeed.service.UserService;
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
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserRequestDto.Login userRequestDto) {
        String token  = userService.login(userRequestDto);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }


    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto.SignUp userRequestDto){
        userService.signUp(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId,@RequestHeader("Authorization") String authorizationHeader, @Valid @RequestBody UserRequestDto.UpdateUser userRequestDto){
        UserResponseDto resultDto = userService.updateUser(userId, authorizationHeader, userRequestDto);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserPw(@PathVariable Long userId,@RequestHeader("Authorization") String authorizationHeader, @Valid @RequestBody UserRequestDto.UpdatePw userRequestDto){
        userService.updateUserPw(userId, authorizationHeader, userRequestDto);
        return new ResponseEntity<>("수정 성공", HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId,@RequestHeader("Authorization") String authorizationHeader, @RequestBody UserRequestDto.DeleteUser userRequestDto){
        userService.deleteUser(userId, authorizationHeader, userRequestDto);
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }
}