package com.knu.ntttt_server.user.model;

import com.knu.ntttt_server.token.model.Artist;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_artist", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "artist_id"})})
public class UserArtist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;


    @Builder
    public UserArtist(User user, Artist artist) {
        this.user = user;
        this.artist = artist;
    }
}
