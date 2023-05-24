package com.knu.ntttt_server.token.model;

public enum Group {
    BTS(Publisher.HIVE), LE_SSERAFIM(Publisher.HIVE),
    Aespa(Publisher.SM), NewJeans(Publisher.HIVE), BLACKPINK(Publisher.YG);

    public final Publisher publisher;

    Group(Publisher publisher) {
        this.publisher = publisher;
    }
}
