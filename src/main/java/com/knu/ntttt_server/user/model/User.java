package com.knu.ntttt_server.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    //Todo: 프로필, 배경사진, 패스워드
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String walletAddr;
    @NotNull
    private String nickname;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;

    @Builder
    public User(Long id, String walletAddr, String nickname, String password, String phoneNumber) {
        this.id = id;
        this.walletAddr = walletAddr;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
