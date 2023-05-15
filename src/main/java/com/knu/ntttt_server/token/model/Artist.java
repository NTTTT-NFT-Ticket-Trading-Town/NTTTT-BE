package com.knu.ntttt_server.token.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Artist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
