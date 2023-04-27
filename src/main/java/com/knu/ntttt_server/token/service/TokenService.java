package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.token.dto.TokenDto;
import com.knu.ntttt_server.token.repository.TokenRepository;
import com.knu.ntttt_server.token.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenRepository tokenRepository;

  /**
   * tokenId로 token Entity를 찾고 tokenDto로 변환 후 반환
   */
  public TokenDto getToken(Long tokenId) {
    Token token = tokenRepository.findById(tokenId)
        .orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST, "해당하는 토큰의 id가 존재하지 않습니다."));
    return TokenDto.fromEntity(token);
  }
}
