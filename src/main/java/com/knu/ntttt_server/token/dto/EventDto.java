package com.knu.ntttt_server.token.dto;

import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.token.model.Publisher;
import lombok.Builder;

public class EventDto {
  public record CreateEventReq(String name, Publisher publisher, String description) {
    public CreateEventReq(String name, Publisher publisher) {
      this(name, publisher, "");
    }
    public Event toEntity() {
      return Event.builder()
              .name(this.name)
              .publisher(this.publisher)
              .description(this.description)
              .quantity(0)
              .build();
    }
  }

  public record EventRes(Long id, String name, Publisher publisher, String description, Integer quantity) {

    public static EventRes from(Event event) {
      return new EventRes(event.getId(), event.getName(), event.getPublisher(), event.getDescription(), event.getQuantity());
    }
  }
}
