package com.knu.ntttt_server.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.knu.ntttt_server.token.model.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {

  public record TokenReq(Long eventId, Long artistId, String imgUrl, String ratio, Long price, String desc) {
    public TokenReq(Long eventId, Long artistId, String imgUrl, Long price) {
      this(eventId, artistId, imgUrl, "", price, "");
    }

    /**
     * 토큰 생성 요청 데이터를 바탕으로 외부에서 연관 객체(혹은 데이터)를 생성
     * 해당 데이터를 주입받아 Token Entity 를 생성한다
     */
    public Token issueToken(Integer seq, Artist artist, Event event, Long nfId, String owner) {
      return Token.builder()
              .imgUrl(this.imgUrl)
              .ratio(this.ratio)
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

  @Builder
  public record TokenRes(EventDto.EventRes event, Long id, Image image, Integer seq,
                         Long price, String desc,
                         Long nftId, ArtistDto.ArtistRes artist, String owner,
                         @JsonProperty("payment_state") PaymentState paymentState) {
    public TokenRes(Token token) {
      this(EventDto.EventRes.from(token.getEvent()), token.getId(), new Image(token.getImgUrl(), token.getRatio()),
              token.getSeq(), token.getPrice(), token.getDescription(),
              token.getNftId(), ArtistDto.ArtistRes.from(token.getArtist()), token.getOwner(), token.getPaymentState());
    }
  }
}
