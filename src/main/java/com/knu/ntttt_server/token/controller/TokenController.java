package com.knu.ntttt_server.token.controller;

import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.token.dto.TokenDto;
import com.knu.ntttt_server.token.service.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

  private TokenService tokenService;

  public TokenController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  /**
   * PathVariable로 id값을 받고,
   * 해당 id값을 가지는 token의 tokenDto를
   * ApiResponse의 data에 실어 반환
   * */
  @GetMapping(value="/token/{id}")
  public ApiResponse<?> token(@PathVariable("id") Long tokenId) {
    TokenDto tokenDto = tokenService.getToken(tokenId);
    return ApiResponse.ok(tokenDto);
  }


}
