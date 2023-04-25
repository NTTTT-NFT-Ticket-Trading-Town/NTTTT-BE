package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.TokenDto;
import com.knu.ntttt_server.token.repository.TokenRepository;
import com.knu.ntttt_server.token.model.Token;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private TokenRepository tokenRepository;
  public TokenService(TokenRepository tokenRepository){
    this.tokenRepository = tokenRepository;
  }

  public TokenDto getToken(Long tokenId){
    Optional<Token> tokenWrapper = tokenRepository.findById(tokenId);
    Token token = tokenWrapper.get();
    TokenDto tokenDto = TokenDto.builder()
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

    return tokenDto;

  }
}
