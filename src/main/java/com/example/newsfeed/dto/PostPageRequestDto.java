package com.example.newsfeed.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPageRequestDto {

    @Min(1)
    private int page = 1;

    @Min(1)
    private int size = 10;
}
