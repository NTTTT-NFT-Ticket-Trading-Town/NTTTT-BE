package com.knu.ntttt_server.token.controller;

import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.token.dto.TokenDto.CreateTokenReq;
import com.knu.ntttt_server.token.model.Token;
import com.knu.ntttt_server.token.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Token")
@RestController
public class TokenController {

  private TokenService tokenService;

  public TokenController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  /**
   */
  @GetMapping(value="/token/{id}")
    @Operation(summary = "단일 토큰 조회", description = "단일 토큰을 조회합니다.")
  public ApiResponse<?> findToken(@PathVariable("id") Long tokenId) {
    return ApiResponse.ok(tokenService.findBy(tokenId));
  }

  @GetMapping(value="/event/token/{eventId}")
  @Operation(summary = "이벤트의 모든 토큰 조회", description = "이벤트의 모든 토큰을 조회합니다.")
  public ApiResponse<?> findTokensInEvent(@PathVariable Long eventId) {
    return ApiResponse.ok(tokenService.findAllBy(eventId));
  }

  @PostMapping("/token")
  @Operation(summary = "토큰 생성", description = "토큰을 생성합니다.")
  public ApiResponse<Token> createToken(CreateTokenReq req) {
    return ApiResponse.ok(tokenService.createToken(req));
  }
}
