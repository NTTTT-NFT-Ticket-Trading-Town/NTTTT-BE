package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.TokenDto.TokenReq;
import com.knu.ntttt_server.token.dto.TokenDto.QueryTokenRes;
import com.knu.ntttt_server.token.model.Token;
import java.util.List;

public interface TokenService {
    Token createToken(TokenReq req);

    List<QueryTokenRes> findAllBy(Long eventId);

    QueryTokenRes findBy(Long tokenId);
}
