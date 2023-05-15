package com.knu.ntttt_server.user.repository;

import com.knu.ntttt_server.user.model.UserArtist;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserArtistRepository extends JpaRepository<UserArtist, Long> {

    List<UserArtist> findAllByUserId(Long userId);
}
