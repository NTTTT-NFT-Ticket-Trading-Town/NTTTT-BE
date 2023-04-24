package com.knu.ntttt_server.token.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * NFT 콜렉션 정보
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {
    @Id @GeneratedValue
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private Publisher publisher;
    @NotNull
    private Integer quantity;

    @Builder
    public Event(String name, Publisher publisher, Integer quantity) {
        this.name = name;
        this.publisher = publisher;
        this.quantity = quantity;
    }
}
