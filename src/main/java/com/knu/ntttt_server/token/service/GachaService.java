package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.GachaDto;
import com.knu.ntttt_server.token.dto.TokenDto;

public interface GachaService {
    GachaDto.GachaRes getGachaToken(String username);
    GachaDto.GachaRes playGacha(String username);
}
