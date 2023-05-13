package com.knu.ntttt_server.user.dto;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Group;
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

    public record ChosenArtistRes(Long artistId, Group group, String name) {
        public ChosenArtistRes(Artist artist) {
            this(artist.getId(), artist.getGroup(), artist.getName());
        }
    }
}
