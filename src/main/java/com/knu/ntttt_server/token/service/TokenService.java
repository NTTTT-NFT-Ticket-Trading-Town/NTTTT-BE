package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.token.dto.TokenDto;
import com.knu.ntttt_server.token.repository.TokenRepository;
import com.knu.ntttt_server.token.model.Token;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private TokenRepository tokenRepository;
  public TokenService(TokenRepository tokenRepository){
    this.tokenRepository = tokenRepository;
  }

  public TokenDto getToken(Long tokenId){
    Token token = tokenRepository.findById(tokenId).orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST));
    return TokenDto.builder()
      .id(token.getId())
      .desc(token.getDesc())
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
