package com.knu.ntttt_server.token.service;


public interface PaymentService {

  Long purchase(String userWalletAddress, Long tokenId);

}
