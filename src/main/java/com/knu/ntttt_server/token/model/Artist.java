package com.knu.ntttt_server.token.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Artist {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "group_name")
    @Enumerated(EnumType.STRING)
    private Group group;

    private String name;

    private String imgUrl; //대표 이미지 주소

    private String ratio;

    @Builder
    public Artist(Group group, String name, String imgUrl, String ratio) {
        this.group = group;
        this.name = name;
        this.imgUrl = imgUrl;
        this.ratio = ratio;
    }
}
