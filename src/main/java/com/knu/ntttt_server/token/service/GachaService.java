package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.GachaDto;

public interface GachaService {
    GachaDto.GachaRes getGachaToken(String username);
    GachaDto.GachaRes playGacha(String username);
    Integer getUserNumberSeeingSameToken(Long tokenId);
}
