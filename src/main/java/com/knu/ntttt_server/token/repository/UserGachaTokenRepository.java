package com.knu.ntttt_server.token.repository;

import com.knu.ntttt_server.token.model.UserGachaToken;
import com.knu.ntttt_server.user.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserGachaTokenRepository extends JpaRepository<UserGachaToken, Long> {
    Optional<UserGachaToken> findByUserAndDate(User user, LocalDate date);

    List<UserGachaToken> findAllByToken_Id(Long tokenId);
}
