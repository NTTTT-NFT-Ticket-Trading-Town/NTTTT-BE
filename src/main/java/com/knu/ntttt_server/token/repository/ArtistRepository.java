package com.knu.ntttt_server.token.repository;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Group;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findAllByGroup(Group group);

    List<Artist> findAllByNameContaining(String name);

    List<Artist> findAllByGroupAndNameContaining(Group group, String name);
}
