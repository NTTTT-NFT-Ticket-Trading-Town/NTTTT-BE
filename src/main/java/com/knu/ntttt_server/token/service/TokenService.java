package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.TokenDto.TokenReq;
import com.knu.ntttt_server.token.dto.TokenDto.TokenRes;
import com.knu.ntttt_server.token.model.Token;
import java.util.List;

public interface TokenService {
    Token createToken(TokenReq req);

    List<TokenRes> findAllBy(Long eventId);

    List<TokenRes> findAllBy(String nickname);

    TokenRes findBy(Long tokenId);

}
