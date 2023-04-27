package com.knu.ntttt_server.token.repository;

import com.knu.ntttt_server.token.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
