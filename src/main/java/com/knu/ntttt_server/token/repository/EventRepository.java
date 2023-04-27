package com.knu.ntttt_server.token.repository;

import com.knu.ntttt_server.token.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
