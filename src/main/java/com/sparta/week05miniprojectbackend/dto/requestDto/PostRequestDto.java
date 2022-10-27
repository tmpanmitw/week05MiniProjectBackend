package com.sparta.week05miniprojectbackend.dto.requestDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequestDto {
    private final String content;
    private final String exercise;
    private final String time;
    private final String date;
}
