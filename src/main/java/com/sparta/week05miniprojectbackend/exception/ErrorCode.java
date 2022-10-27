package com.sparta.week05miniprojectbackend.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND_EXCEPTION("404", "Bad Request", "유저 정보를 찾을 수 없습니다"),
    USER_ID_ALREADY_EXCEPTION("400", "Bad Request", "이미 아이디가 존재합니다"),
    USER_NOT_EQUALS_EXCEPTION("401", "Unauthorized", "작성자만 수정할 수 있습니다"),
    POST_NOT_FOUND_EXCEPTION("404", "Bad Request", "게시글 정보를 찾을 수 없습니다"),
    COMMENT_NOT_FOUND_EXCEPTION("404", "Bad Request", "댓글 정보를 찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error", "서버 오류");

    private String code;
    private String message;
    private String detail;

    ErrorCode(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}
