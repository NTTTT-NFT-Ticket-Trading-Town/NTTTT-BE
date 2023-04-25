package com.knu.ntttt_server.repository;

import com.knu.ntttt_server.token.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long>{

}
