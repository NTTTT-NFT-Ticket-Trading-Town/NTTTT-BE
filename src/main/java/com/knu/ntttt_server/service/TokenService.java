package com.knu.ntttt_server.service;

import com.knu.ntttt_server.dto.TokenDto;
import com.knu.ntttt_server.repository.TokenRepository;
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
      .description(token.getDescription())
      .event(token.getEvent())
      .imgUrl(token.getImgUrl())
      .nftId(token.getNftId())
      .paymentState(token.getPaymentState())
      .price(token.getPrice())
      .seq(token.getSeq())
      .build();

    return tokenDto;

  }
}
