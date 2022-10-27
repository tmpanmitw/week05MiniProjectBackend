package com.sparta.week05miniprojectbackend.entity;

import com.sparta.week05miniprojectbackend.dto.requestDto.UserRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "users")  // table name 은 'user' 가 아닌 'users' 로 변경
@NoArgsConstructor
public class User extends Timestamped  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordCheck;
    @NotBlank
    private String nickName;

    public User(UserRequestDto userRequestDto) {
        this.userId = userRequestDto.getUserId();
        this.password = userRequestDto.getPassword();
        this.passwordCheck = userRequestDto.getPasswordCheck();
        this.nickName = userRequestDto.getNickName();
    }
}
