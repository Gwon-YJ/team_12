package com.example.newsfeed.post.dto;

import java.util.List;

public class PostPageInfoResponseDto<T> {

    private final List<T> contents;

    private final int page;

    private final int size;

    public PostPageInfoResponseDto(List<T> contents, int page, int size) {
        this.contents = contents;
        this.page = page;
        this.size = size;
    }

    public static <T> PostPageInfoResponseDto<T> toDto
}
