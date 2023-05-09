package com.knu.ntttt_server.gacha.repository;

import com.knu.ntttt_server.token.model.PaymentState;
import com.knu.ntttt_server.token.model.Token;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenGachaRepository extends JpaRepository<Token, Long> {
    List<Token> findTokensByPaymentState(PaymentState state);
}
