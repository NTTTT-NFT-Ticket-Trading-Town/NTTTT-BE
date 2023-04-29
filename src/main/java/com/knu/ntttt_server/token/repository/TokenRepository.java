package com.knu.ntttt_server.token.repository;

import com.knu.ntttt_server.token.model.Token;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> queryAllByEvent_Id(Long id);
}
