package com.knu.ntttt_server.gacha.service;

import com.knu.ntttt_server.token.model.Token;

public interface TokenGachaService {
    Token gacha(Long userId);
}
