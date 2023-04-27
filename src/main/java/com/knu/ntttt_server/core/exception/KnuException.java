package com.knu.ntttt_server.core.exception;

import com.knu.ntttt_server.core.response.ResultCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class KnuException extends RuntimeException {
    private ResultCode resultCode = ResultCode.INTERNAL_SERVER_ERROR;

    public KnuException(ResultCode resultCode) {
        super();
        this.resultCode = resultCode;
    }


    public KnuException(String message) {
        super(message);
        this.resultCode.setMessage(message);
    }

    public KnuException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
        this.resultCode.setMessage(message);
    }

    public KnuException() {
        super();
    }
}
