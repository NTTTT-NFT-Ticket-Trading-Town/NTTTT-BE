package com.knu.ntttt_server.token.controller;

import com.knu.ntttt_server.core.annotation.CurrentUser;
import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.token.dto.TokenDto;
import com.knu.ntttt_server.token.service.GachaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Gacha")
@RequiredArgsConstructor
@RestController
@RequestMapping("/gacha")
@CrossOrigin("*")
public class GachaTokenController {

    private final GachaService gachaService;

    @Operation(summary = "가챠를 돌렸던 토큰 조회.", description = "가챠를 돌렸던 토큰을 조회합니다.")
    @GetMapping()
    public ApiResponse<TokenDto.TokenRes> getGachaToken(@CurrentUser User user) {
        return ApiResponse.ok(gachaService.getGachaToken(user.getUsername()));
    }

    @Operation(summary = "가챠를 돌립니다.", description = "가챠를 돌립니다.")
    @PostMapping()
    public ApiResponse<TokenDto.TokenRes> playGacha(@CurrentUser User user) {
        return ApiResponse.ok(gachaService.playGacha(user.getUsername()));
    }
}
