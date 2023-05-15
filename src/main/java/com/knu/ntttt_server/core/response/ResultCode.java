package com.knu.ntttt_server.core.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
public enum ResultCode {

    /*
     status(3자리)_숫자(3자리)
     */
    SUCCESS(HttpStatus.OK, 200_001, "success"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500_001, "internal server error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400_001, "cannot find token"),
    NOT_FOUND(HttpStatus.NOT_FOUND, 404_001, "not found"),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, 404_002, "토큰이 존재하지 않습니다."),
    TOKEN_SOLD_OUT(HttpStatus.NOT_FOUND, 404_002, "토큰이 이미 팔렸습니다.");

    private HttpStatus httpStatus;
    private int code;
    private String message;

    ResultCode(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
