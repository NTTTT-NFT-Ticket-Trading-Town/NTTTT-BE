package com.knu.ntttt_server.core.exception;

import com.knu.ntttt_server.core.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({KnuException.class})
    public ApiResponse<?> knuExceptionHandler(KnuException e, HttpServletResponse response) {
        response.setStatus(e.getResultCode().getHttpStatus().value());
        return ApiResponse.error(e);
    }

    @ExceptionHandler({RuntimeException.class})
    public ApiResponse<?> RuntimeHandler(RuntimeException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ApiResponse.error();
    }
}
