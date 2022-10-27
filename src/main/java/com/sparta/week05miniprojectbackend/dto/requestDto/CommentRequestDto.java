package com.sparta.week05miniprojectbackend.dto.requestDto;


import com.sparta.week05miniprojectbackend.entity.Comment;
import com.sparta.week05miniprojectbackend.entity.Post;
import com.sparta.week05miniprojectbackend.entity.User;
import lombok.Data;

import java.lang.reflect.Member;

@Data
public class CommentRequestDto {

    private String content;

}
