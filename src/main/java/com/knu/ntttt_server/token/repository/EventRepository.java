package com.knu.ntttt_server.token.repository;

import com.knu.ntttt_server.token.model.Event;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Event> findByIdWithLock(Long eventId);
}
