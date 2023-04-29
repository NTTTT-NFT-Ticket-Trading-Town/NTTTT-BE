package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.TokenDto.QueryTokenRes;

public interface PaymentService {
  /**
   * (유저 지갑 정보, 토큰 id) 구매 요청
   * -> 해당 지갑으로 토큰 전송(NftService 의 transferNft 확인)
   * 토큰 상태 변경 정도만
   */

  QueryTokenRes purchase(String userWalletAddress, Long tokenId);
  void updatePaymentState(Long tokenId);

}
