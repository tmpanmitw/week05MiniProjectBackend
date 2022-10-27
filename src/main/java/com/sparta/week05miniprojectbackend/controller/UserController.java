package com.sparta.week05miniprojectbackend.controller;


import com.sparta.week05miniprojectbackend.dto.requestDto.IdCheckRequestDto;
import com.sparta.week05miniprojectbackend.dto.requestDto.LoginRequestDto;
import com.sparta.week05miniprojectbackend.dto.requestDto.UserRequestDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.ResponseDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.UserResponseDto;
import com.sparta.week05miniprojectbackend.jwt.util.JwtUtil;
import com.sparta.week05miniprojectbackend.security.user.UserDetailsImpl;
import com.sparta.week05miniprojectbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    // 회원가입
    @PostMapping("/user/signup")
    public ResponseDto<?> signup(@RequestBody @Valid UserRequestDto userRequestDto){
        return userService.signup(userRequestDto);
    }

    // ID 중복확인
    @PostMapping("/user/idCheck")
    public ResponseDto<?> idCheck(@RequestBody @Valid IdCheckRequestDto idCheckRequestDto){
        return userService.idCheck(idCheckRequestDto);
    }

    // 로그인
    @PostMapping("/user/login")
    public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }

    // 토큰 재발행
    @GetMapping("/issue/token")
    public UserResponseDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        response.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getUser().getUserId(), "Access"));
        return new UserResponseDto("Success IssuedToken", HttpStatus.OK.value());
    }

//    @PostMapping
//    public HttpHeaders setHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
//        return headers;
//    }
}


