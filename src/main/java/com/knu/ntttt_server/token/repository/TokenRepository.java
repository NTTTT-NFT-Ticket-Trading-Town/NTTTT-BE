package com.knu.ntttt_server.token.repository;

import com.knu.ntttt_server.token.model.Token;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> queryAllByEvent_Id(Long id);

    List<Token> queryAllByOwner(String walletAddress);

    @Query(value = "SELECT t.* FROM user_artist ua JOIN token t ON ua.artist_id = t.artist_id " +
            "WHERE ua.user_id = :userId AND t.payment_state = 'ON_SALE' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Token> findRandomTokenByUserIdAndPaymentStateOnSale(Long userId);
}
