package com.sparta.week05miniprojectbackend.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String errorCdoe;
    private String errorMessage;
    private String errorDetail;

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public ErrorResponse(ErrorCode code) {
        this.errorCdoe = code.getCode();
        this.errorMessage = code.getMessage();
        this.errorDetail = code.getDetail();
    }
}
