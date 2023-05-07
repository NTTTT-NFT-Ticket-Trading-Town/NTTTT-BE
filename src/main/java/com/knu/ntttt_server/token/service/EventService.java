package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.dto.EventDto.CreateEventReq;
import com.knu.ntttt_server.token.model.Event;
import java.util.List;

public interface EventService {
    Event createEvent(CreateEventReq event);

    Event findBy(Long eventId);

    List<Event> findAll();
}
