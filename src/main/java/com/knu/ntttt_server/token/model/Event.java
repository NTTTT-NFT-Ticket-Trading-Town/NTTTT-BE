package com.knu.ntttt_server.token.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
  @Id @GeneratedValue
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

  //TODO(토큰 발행 시퀀스 관리 로직 리팩토링)
  public Integer getQuantity() {
    return quantity++;
  }
}
