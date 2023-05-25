package com.knu.ntttt_server.user.repository;

import com.knu.ntttt_server.user.model.User;
import com.knu.ntttt_server.user.model.UserArtist;
import java.util.List;
import java.util.Optional;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserArtistRepository extends JpaRepository<UserArtist, Long> {

    List<UserArtist> findAllByUserId(Long userId);

    boolean existsByUserId(Long userId);

    void deleteAllByUser_Id(Long userId);
}
