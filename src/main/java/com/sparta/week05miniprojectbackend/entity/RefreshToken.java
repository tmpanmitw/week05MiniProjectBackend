package com.sparta.week05miniprojectbackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String refreshToken;
    @NotBlank
    private String accountUserId;

    public RefreshToken(String token, String userId) {
        this.refreshToken = token;
        this.accountUserId = userId;
    }
    public RefreshToken updateToken(String token){
        this.refreshToken = token;
        return this;
    }
}
