package com.knu.ntttt_server.user.dto;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.user.model.User;
import com.knu.ntttt_server.user.model.UserArtist;

public class UserArtistDto {

    public record ChooseArtistReq(User user, Artist artist) {

        public UserArtist createUserArtistPair() {

            return UserArtist.builder()
                .user(user)
                .artist(artist)
                .build();
        }
    }
}
