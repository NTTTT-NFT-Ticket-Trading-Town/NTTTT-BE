package com.knu.ntttt_server.token.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 발행 완료된 NFT 의 메타데이터 정보
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
  @Id @GeneratedValue
  private Long id;

  @NotNull @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;
  @NotNull
  private Long seq;
  @NotNull
  private String imgUrl;

  @ManyToOne @NotNull
  private Artist artist;
  
  @NotNull
  private Long price;
  private String description;

  @NotNull
  private Long nftId;

  @NotNull
  private PaymentState paymentState = PaymentState.ON_SALE;

  @Builder
  public Token(Event event, Long seq, String imgUrl, Artist artist, Long price, String description, Long nftId) {
    this.event = event;
    this.seq = seq;
    this.imgUrl = imgUrl;
    this.artist = artist;
    this.price = price;
    this.description = description;
    this.nftId = nftId;
  }
}
