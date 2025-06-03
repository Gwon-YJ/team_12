package com.example.newsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostRequestDto {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
