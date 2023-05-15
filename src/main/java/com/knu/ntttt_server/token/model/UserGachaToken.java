package com.knu.ntttt_server.token.model;

import com.knu.ntttt_server.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGachaToken {

    private static final int DEFAULT_CHANCE_NUMBER = 5;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "token_id")
    private Token token;

    @NotNull
    private LocalDate date;

    private int chance = DEFAULT_CHANCE_NUMBER;
}
