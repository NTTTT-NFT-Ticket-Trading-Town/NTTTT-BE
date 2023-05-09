package com.knu.ntttt_server.token.model;

import lombok.Getter;

@Getter
public class Image {
    public String url;
    public String ratio;

    public Image(String url, String ratio) {
        this.url = url;
        this.ratio = ratio;
    }
}
