package com.example.newsfeed.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostPageInfoResponseDto<T> {

    private final List<T> contents;

    private final int page;

    private final int size;

    public PostPageInfoResponseDto(List<T> contents, int page, int size) {
        this.contents = contents;
        this.page = page;
        this.size = size;
    }

    public static <T> PostPageInfoResponseDto<T> toDto(List<T> contents, int page, int size){
        return new PostPageInfoResponseDto<>(contents, page, size);
    }
}
