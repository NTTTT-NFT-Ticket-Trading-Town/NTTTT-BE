package com.knu.ntttt_server.token.dto;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.token.model.Token;
import lombok.Getter;

@Getter
public class TokenDto {
  public record CreateTokenReq(Long eventId, Long artistId, String imgUrl, Long price, String desc) {
    public CreateTokenReq(Long eventId, Long artistId, String imgUrl, Long price) {
      this(eventId, artistId, imgUrl, price, "");
    }

    /**
     * 토큰 생성 요청 데이터를 바탕으로 외부에서 연관 객체(혹은 데이터)를 생성
     * 해당 데이터를 주입받아 Token Entity 를 생성한다
     */
    public Token issueToken(Long seq, Artist artist, Event event, Long nfId) {
      return Token.builder()
              .imgUrl(this.imgUrl)
              .price(this.price)
              .description(this.desc)
              .artist(artist)
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
