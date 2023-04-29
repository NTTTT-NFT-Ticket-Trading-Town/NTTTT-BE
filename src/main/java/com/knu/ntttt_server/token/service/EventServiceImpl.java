package com.knu.ntttt_server.token.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.token.dto.EventDto.CreateEventReq;
import com.knu.ntttt_server.token.repository.EventRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public Event createEvent(CreateEventReq req) {
        return eventRepository.save(req.toEntity());
    }

    @Override
    public Event findBy(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new KnuException("요청 이벤트(콜렉션) 을 찾을 수 없습니다."));
        return event;
    }
}
