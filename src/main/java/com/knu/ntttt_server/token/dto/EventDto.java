package com.knu.ntttt_server.token.dto;

import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.token.model.Publisher;
import lombok.Builder;

public class EventDto {

  private Long id;
  private String name;
  private Publisher publisher;
  private Integer quantity;
  private String description;

  @Builder
  public EventDto(Long id, String name, Publisher publisher, Integer quantity, String description) {
    this.id = id;
    this.name = name;
    this.publisher = publisher;
    this.quantity = quantity;
    this.description = description;
  }

  public Event toEntity() {
    return Event.builder()
        .name(name)
        .publisher(publisher)
        .quantity(quantity)
        .description(description)
        .build();
  }
}
