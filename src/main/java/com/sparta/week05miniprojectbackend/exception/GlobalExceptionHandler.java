package com.sparta.week05miniprojectbackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 유저 이메일 중복에러
    @ExceptionHandler(value = UserIdAlreadyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleUserEmailAlreadyException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.USER_ID_ALREADY_EXCEPTION);
        log.error("error : {}, stacktrace: {}", errorResponse, e.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // 유저 존재여부 에러
    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        log.error("error : {}, stacktrace: {}", errorResponse, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // 게시글 존재여부 에러
    @ExceptionHandler(value = PostNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.POST_NOT_FOUND_EXCEPTION);
        log.error("error : {}, stacktrace: {}", errorResponse, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // 작성자만 수정 가능 에러
    @ExceptionHandler(value = UserNotEqualsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleUserNotEqualsException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.USER_NOT_EQUALS_EXCEPTION);
        log.error("error : {}, stacktrace: {}", errorResponse, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // 댓글 존재 여부 에러
    @ExceptionHandler(value = CommentNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleCommentNotFoundException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.COMMENT_NOT_FOUND_EXCEPTION);
        log.error("error : {}, stacktrace: {}", errorResponse, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    // 500 에러
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException0(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        log.error("error : {}, stacktrace: {}", errorResponse, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
