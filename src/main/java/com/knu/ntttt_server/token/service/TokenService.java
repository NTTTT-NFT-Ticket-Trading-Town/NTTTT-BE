package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.TokenDto.CreateTokenReq;
import com.knu.ntttt_server.token.dto.TokenDto.QueryTokenRes;
import com.knu.ntttt_server.token.model.Token;
import java.util.List;

public interface TokenService {
    Token createToken(CreateTokenReq req);

    List<QueryTokenRes> queryAllBy(Long eventId);

    QueryTokenRes queryToken(Long tokenId);
}
