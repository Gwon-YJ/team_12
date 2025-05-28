package com.example.newsfeed.dto;

import com.example.newsfeed.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime localDateTime;
    private final LocalDateTime modifiedAt;

    public UserResponseDto(User user){
        this.id = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.localDateTime = user.getLocalDateTime();
        this.modifiedAt = user.getModifiedAt();
    }
}
