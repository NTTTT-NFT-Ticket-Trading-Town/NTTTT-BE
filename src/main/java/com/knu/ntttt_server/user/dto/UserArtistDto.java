package com.knu.ntttt_server.user.dto;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.user.model.User;
import com.knu.ntttt_server.user.model.UserArtist;

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

    public record ChosenArtistRes(String name) {
        public ChosenArtistRes(Artist artist) {
            this(artist.getName());
        }
    }
}
