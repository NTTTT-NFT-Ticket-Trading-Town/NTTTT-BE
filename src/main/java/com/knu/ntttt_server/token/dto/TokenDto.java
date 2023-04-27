package com.knu.ntttt_server.token.dto;

import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.token.model.Token;
import lombok.Getter;

@Getter
public class TokenDto {
  public record CreateTokenReq(Long eventId, String imgUrl, Long price, String desc) {
    public CreateTokenReq(Long eventId, String imgUrl, Long price) {
      this(eventId, imgUrl, price, "");
    }

    public Token issueToken(Long seq, Event event, Long nfId) {
      return Token.builder()
              .imgUrl(this.imgUrl)
              .price(this.price)
              .description(this.desc)
              .nftId(nfId)
              .event(event)
              .seq(seq)
              .build();
    }
  }

  public record QueryTokenRes(Long id, String imgUrl,
                              Long price, String desc,
                              Long nftId, String owner) {
    public QueryTokenRes(Token token, String owner) {
      this(token.getId(), token.getImgUrl(),
              token.getPrice(), token.getDescription(),
              token.getNftId(), owner);
    }
  }
}
