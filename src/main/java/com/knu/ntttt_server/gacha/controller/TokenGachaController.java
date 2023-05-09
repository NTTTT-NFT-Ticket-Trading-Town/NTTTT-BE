package com.knu.ntttt_server.gacha.controller;

import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.gacha.service.TokenGachaService;
import com.knu.ntttt_server.token.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenGachaController {
    private final TokenGachaService tokenGachaService;

    @PostMapping("/gacha")
    public ApiResponse<Token> gacha(Long userId) {
        return ApiResponse.ok(tokenGachaService.gacha(userId));
    }
}
