package com.knu.ntttt_server.token.dto;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.token.model.Token;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public Token issueToken(Integer seq, Artist artist, Event event, Long nfId, String owner) {
      return Token.builder()
              .imgUrl(this.imgUrl)
              .price(this.price)
              .description(this.desc)
              .artist(artist)
              .nftId(nfId)
              .event(event)
              .seq(seq)
              .publishedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
              .owner(owner)
              .build();
    }
  }

  public record QueryTokenRes(Event event, Long id, String imgUrl, Integer seq,
                              Long price, String desc,
                              Long nftId, String owner) {
    public QueryTokenRes(Token token, String owner) {
      this(token.getEvent(), token.getId(), token.getImgUrl(),
              token.getSeq(), token.getPrice(), token.getDescription(),
              token.getNftId(), owner);
    }
  }
}
