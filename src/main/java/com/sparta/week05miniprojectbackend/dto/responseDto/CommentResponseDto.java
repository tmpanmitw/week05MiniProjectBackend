package com.sparta.week05miniprojectbackend.dto.responseDto;

import com.sparta.week05miniprojectbackend.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String nickName;
    private LocalDateTime createAt;

    // Post타입에서 DTO타입으로 변환
    public CommentResponseDto(Comment comment){

        this.id = comment.getId();

        this.comment = comment.getContent();

        this.nickName = comment.getUser().getNickName();

        this.createAt = comment.getCreateAt();

    }
}
