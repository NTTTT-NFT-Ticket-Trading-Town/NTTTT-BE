package com.knu.ntttt_server.token.model;

import lombok.Getter;

@Getter
public class Image {
    public String imageUrl;
    public String ratio;

    public Image(String imageUrl, String ratio) {
        this.imageUrl = imageUrl;
        this.ratio = ratio;
    }
}
