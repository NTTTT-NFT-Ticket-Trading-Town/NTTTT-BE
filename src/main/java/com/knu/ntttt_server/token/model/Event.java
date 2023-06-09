package com.knu.ntttt_server.token.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * NFT 콜렉션 정보
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String name;
  @NotNull @Enumerated(EnumType.STRING)
  private Publisher publisher;
  @NotNull
  private Integer quantity;
  private String description;

  @Builder
  public Event(String name, Publisher publisher, Integer quantity, String description) {
    this.name = name;
    this.publisher = publisher;
    this.quantity = quantity;
    this.description = description;
  }

  // 이벤트의 다음 시퀀스 번호를 불러온다
  public Integer getNextSequenceNumber() {
    return ++quantity;
  }
}
