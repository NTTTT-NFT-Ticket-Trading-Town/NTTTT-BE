package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.TokenDto.CreateTokenReq;
import com.knu.ntttt_server.token.dto.TokenDto.QueryTokenRes;
import com.knu.ntttt_server.token.model.Token;

public interface TokenService {
    Token createToken(CreateTokenReq req);
    QueryTokenRes queryToken(Long tokenId);
}
