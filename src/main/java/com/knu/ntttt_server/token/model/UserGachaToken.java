package com.knu.ntttt_server.token.model;

import com.knu.ntttt_server.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "USER_GACHA_TOKEN", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"})})
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

    public UserGachaToken(User user, Token token) {
        this.user = user;
        this.token = token;
    }

    /**
     * 새로운 랜덤 가챠 토큰으로 수정하고
     * 기회를 1회 감소시킨다.
     * @param token
     */
    public void playGacha(Token token) {
        this.token = token;
        this.chance--;
    }
}
