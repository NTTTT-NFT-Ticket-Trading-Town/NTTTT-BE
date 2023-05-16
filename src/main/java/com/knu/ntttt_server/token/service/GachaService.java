package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.TokenDto;

public interface GachaService {
    TokenDto.TokenRes getGachaToken(String username);
    TokenDto.TokenRes playGacha(String username);
}
