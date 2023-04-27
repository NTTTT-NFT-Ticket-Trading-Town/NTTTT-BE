package com.knu.ntttt_server.token.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Artist {
    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Group group;

    private String name;

    public Artist(Group group, String name) {
        this.group = group;
        this.name = name;
    }
}
