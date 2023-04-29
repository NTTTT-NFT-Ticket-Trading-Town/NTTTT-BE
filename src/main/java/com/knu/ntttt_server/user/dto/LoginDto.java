package com.knu.ntttt_server.user.dto;

import lombok.Getter;

@Getter
public class LoginDto {

    private final String nickname;
    private final String password;

    public LoginDto(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
