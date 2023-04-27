package com.knu.ntttt_server.token.dto;

import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.token.model.PaymentState;
import com.knu.ntttt_server.token.model.Token;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {

  private Long id;
  private Event event;
  private Long seq;
  private String imgUrl;
  private Long price;
  private String description;
  private Long nftId;
  private PaymentState paymentState;
  private String owner;

  @Builder
  public TokenDto(Long id, Event event, Long seq, String imgUrl, Long price, String description, Long nftId, PaymentState paymentState, String owner) {
    this.id = id;
    this.event = event;
    this.seq = seq;
    this.imgUrl = imgUrl;
    this.price = price;
    this.description = description;
    this.nftId = nftId;
    this.paymentState = paymentState;
    this.owner = owner;
  }

  public static TokenDto fromEntity(Token token) {
    return TokenDto.builder()
        .id(token.getId())
        .description(token.getDescription())
        .event(token.getEvent())
        .imgUrl(token.getImgUrl())
        .nftId(token.getNftId())
        .paymentState(token.getPaymentState())
        .price(token.getPrice())
        .seq(token.getSeq())
        .owner(token.getOwner())
        .build();
  }

  public Token toEntity() {
    return Token.builder()
        .event(event)
        .seq(seq)
        .imgUrl(imgUrl)
        .price(price)
        .description(description)
        .nftId(nftId)
        .paymentState(paymentState)
        .owner(owner)
        .build();
  }
}
