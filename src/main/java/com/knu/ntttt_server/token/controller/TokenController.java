package com.knu.ntttt_server.token.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.token.dto.TokenDto;
import com.knu.ntttt_server.token.service.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

  private TokenService tokenService;
  public TokenController(TokenService tokenService){
    this.tokenService = tokenService;
  }

  @GetMapping(value="/token/{id}")
  public ApiResponse<?> token(@PathVariable("id") Long tokenId) {
    TokenDto tokenDto = tokenService.getToken(tokenId);
    return ApiResponse.ok(tokenDto);
  }


}
