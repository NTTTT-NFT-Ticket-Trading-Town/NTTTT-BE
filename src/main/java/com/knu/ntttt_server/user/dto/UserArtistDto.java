package com.knu.ntttt_server.user.dto;

import com.knu.ntttt_server.token.dto.TokenDto.TokenRes;
import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Group;
import com.knu.ntttt_server.user.model.User;
import com.knu.ntttt_server.user.model.UserArtist;
import java.util.List;

public class UserArtistDto {

    public record ChooseArtistReq(Long artistId) {}

    public record UserArtistReq(User user, Artist artist) {

        public UserArtist toEntity() {

            return UserArtist.builder()
                .user(user)
                .artist(artist)
                .build();
        }
    }

    /**
     * @param gachaList 유저가 보유하고 있는 토큰 리스트
     * @param categoryList 유저가 선택한 아티스트 리스트
     */
    public record UserTokenArtistRes(List<TokenRes> gachaList, List<ChosenArtistRes> categoryList) {}

    public record ChosenArtistRes(String name, Group group) {
        public ChosenArtistRes(Artist artist) {
            this(artist.getName(), artist.getGroup());
        }
    }
}
