package com.knu.ntttt_server.token.model;

public enum Group {
    BTS(Publisher.HIVE), Aespa(Publisher.SM);

    public Publisher publisher;

    Group(Publisher publisher) {
        this.publisher = publisher;
    }
}
