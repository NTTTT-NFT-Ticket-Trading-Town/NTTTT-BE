package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.nft.service.NftService;
import com.knu.ntttt_server.token.model.PaymentState;
import com.knu.ntttt_server.token.model.Token;
import com.knu.ntttt_server.token.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
  private final NftService nftService;
  private final TokenRepository tokenRepository;

  /**
   * tokenID를 가진 token 찾기
   */
  Token getToken(Long tokenId) {
    return tokenRepository.findById(tokenId)
        .orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST, "존재하지 않는 토큰입니다."));
  }

  /**
   * 사용자 지갑 주소로 token 전송
   */
  @Override
  @Transactional
  public Long purchase(String userWalletAddress, Long tokenId) {
    Token token = getToken(tokenId);
    nftService.transferNft(userWalletAddress, token.getNftId());
    this.updatePaymentState(token);
    return token.getId();
  }

  /**
   * 토큰 paymentState를 SOLD_OUT으로 변경
   */
  @Override
  public void updatePaymentState(Token token) {
    token.updatePaymentState(PaymentState.SOLD_OUT);
    tokenRepository.save(token);
  }
}
