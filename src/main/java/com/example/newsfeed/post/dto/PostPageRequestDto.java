package com.example.newsfeed.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPageRequestDto {

    private int page = 1;

    private int size = 10;
}
