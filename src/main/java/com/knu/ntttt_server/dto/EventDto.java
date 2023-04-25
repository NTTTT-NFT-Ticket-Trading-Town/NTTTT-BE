package com.knu.ntttt_server.dto;

import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.token.model.Publisher;
import lombok.Builder;

public class EventDto {
  private Long id;
  private String name;
  private Publisher publisher;
  private Integer quantity;
  private String desc;

  @Builder
  public EventDto(Long id, String name, Publisher publisher, Integer quantity, String desc){
    this.id = id;
    this.name = name;
    this.publisher = publisher;
    this.quantity = quantity;
    this.desc = desc;
  }

  public Event toEntity(String name, Publisher publisher, Integer quantity, String desc){
    Event event = Event.builder()
      .name(name)
      .publisher(publisher)
      .quantity(quantity)
      .desc(desc)
      .build();

    return event;
  }
}
