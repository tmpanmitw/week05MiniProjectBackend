package com.sparta.week05miniprojectbackend.dto.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class IdCheckRequestDto {

    @NotBlank(message="아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z[0-9]]{8,16}$",
            message = "아이디는 영문 대, 소문자와 숫자가 적어도 1개이상씩 포함된 8자 ~ 16자의 아이디여야 합니다.")
    private String userId;

    public IdCheckRequestDto(String userId) {
        this.userId = userId;
    }
}
