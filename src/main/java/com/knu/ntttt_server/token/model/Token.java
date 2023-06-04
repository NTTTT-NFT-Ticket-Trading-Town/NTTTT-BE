package com.knu.ntttt_server.token.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;
  @NotNull
  private Integer seq;
  @NotNull
  private String imgUrl;

  @NotNull
  private String ratio = " ";

  @ManyToOne @NotNull
  private Artist artist;
  
  @NotNull
  private Long price;
  private String description;

  @NotNull
  private Long nftId;

  @NotNull
  @Enumerated(EnumType.STRING)
  private PaymentState paymentState = PaymentState.ON_SALE;

  @NotNull
  private LocalDateTime publishedAt;

  @NotNull
  private String owner;

  @Builder
  public Token(Event event, Integer seq, String imgUrl, String ratio, Artist artist, Long price, String description, Long nftId, LocalDateTime publishedAt, String owner){
    this.event = event;
    this.seq = seq;
    this.imgUrl = imgUrl;
    this.ratio = ratio;
    this.artist = artist;
    this.price = price;
    this.description = description;
    this.nftId = nftId;
    this.publishedAt = publishedAt;
    this.owner = owner;
  }

  public void soldOut() {
    this.paymentState = PaymentState.SOLD_OUT;
  }

  /**
   * 토큰 소유자 변경
   */
  public void changeOwner(String newWalletAddr) {
    this.owner = newWalletAddr;
  }
}
