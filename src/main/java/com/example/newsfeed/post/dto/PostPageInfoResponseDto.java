package com.example.newsfeed.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostPageInfoResponseDto{

    private final List<PostPageResponseDto> contents;

    private final int page;

    private final int size;

    private final int totalPages;

    private final long totalElements;

    public PostPageInfoResponseDto(List<PostPageResponseDto> contents, int page, int size, int totalPages, long totalElements) {
        this.contents = contents;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public static PostPageInfoResponseDto toDto(List<PostPageResponseDto> contents, int page, int size, int totalPages, long totalElements){
        return new PostPageInfoResponseDto(contents, page, size, totalPages, totalElements);
    }
}
