package com.sparta.week05miniprojectbackend.service;


import com.sparta.week05miniprojectbackend.dto.requestDto.IdCheckRequestDto;
import com.sparta.week05miniprojectbackend.dto.requestDto.LoginRequestDto;
import com.sparta.week05miniprojectbackend.dto.requestDto.UserRequestDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.ResponseDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.UserResponseDto;
import com.sparta.week05miniprojectbackend.entity.RefreshToken;
import com.sparta.week05miniprojectbackend.entity.User;
import com.sparta.week05miniprojectbackend.jwt.dto.TokenDto;
import com.sparta.week05miniprojectbackend.jwt.util.JwtUtil;
import com.sparta.week05miniprojectbackend.repository.RefreshTokenRepository;
import com.sparta.week05miniprojectbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    // 회원가입
    public ResponseDto<?> signup(UserRequestDto userRequestDto){
        // userid 중복 검사
        // Exception 값 컨펌!
        if(userRepository.findByUserId(userRequestDto.getUserId()).isPresent()){
            throw new RuntimeException("Overlap Check");
        }
        // password 값 인코딩 후 Dto 에 재주입 후 Dto 를 Entity 로  변환
        userRequestDto.setEncodePwd(passwordEncoder.encode(userRequestDto.getPassword()));
        User user = new User(userRequestDto);

        userRepository.save(user);
        return ResponseDto.success(
                "회원가입 성공"
        );
    }

    // ID 중복확인
    public ResponseDto<?> idCheck(IdCheckRequestDto idCheckRequestDto){
        String result = "";
        if(userRepository.findByUserId(idCheckRequestDto.getUserId()).isPresent()) {
            result = "ID가 이미 존재합니다.";
        }else{
            result = "사용가능한 ID 입니다.";
            }
        return ResponseDto.success(result);
        }

    // 로그인
    @Transactional
    public ResponseDto<?> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // userId 로 user 정보 호출
        User user = userRepository.findByUserId(loginRequestDto.getUserId()).orElseThrow(
                () -> new RuntimeException("Not found Account")
        );
        // password 일치 여부 확인 -> password 인코딩
        if (passwordEncoder.matches(passwordEncoder.encode(loginRequestDto.getPassword()), user.getPassword())) {
            throw new RuntimeException("Not matches Password");
        }
        // userId 값을 포함한 토큰 생성 후 tokenDto 에 저장
        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getUserId());

        // userId 값에 해당하는 refreshToken 을 DB에서 가져옴
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountUserId(loginRequestDto.getUserId());

        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getUserId());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

         return ResponseDto.success(
                 "로그인 성공"
         );
    }
    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }
}
