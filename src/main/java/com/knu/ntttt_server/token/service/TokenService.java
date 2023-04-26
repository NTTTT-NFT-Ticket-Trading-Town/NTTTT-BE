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

  private TokenRepository tokenRepository;

  public TokenService(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  /**
   * findById로 tokenId인 tokenEntity를 찾음
   * tokenId에 해당하는 token이 없으면 예외를 던짐
   * code: 400_001, message: cannot find token
   * */
  public TokenDto getToken(Long tokenId) {
    Token token = tokenRepository.findById(tokenId)
        .orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST));
    return TokenDto.builder()
        .id(token.getId())
        .description(token.getDescription())
        .event(token.getEvent())
        .imgUrl(token.getImgUrl())
        .nftId(token.getNftId())
        .paymentState(token.getPaymentState())
        .price(token.getPrice())
        .seq(token.getSeq())
        .owner(token.getOwner())
        .build();

  }
}
