package com.sparta.week05miniprojectbackend.dto.responseDto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String content;
    private String exercise;
    private String time;
    private String date;
    private String nickName;

    private List<CommentResponseDto> commentResponseDtoList;
    private List<ImgResponseDto> imgResponseDtoList;
}
