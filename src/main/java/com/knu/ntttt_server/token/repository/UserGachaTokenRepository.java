package com.knu.ntttt_server.token.repository;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.PaymentState;
import com.knu.ntttt_server.token.model.Token;
import com.knu.ntttt_server.token.model.UserGachaToken;
import com.knu.ntttt_server.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface UserGachaTokenRepository extends JpaRepository<UserGachaToken, Long> {
    Optional<UserGachaToken> findByUserAndDate(User user, LocalDate date);
}
