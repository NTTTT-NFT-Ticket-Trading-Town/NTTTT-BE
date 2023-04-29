package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.model.Token;

public interface PaymentService {

  Long purchase(String userWalletAddress, Long tokenId);
  void updatePaymentState(Token token);

}
