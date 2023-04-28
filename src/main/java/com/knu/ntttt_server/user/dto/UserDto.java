package com.knu.ntttt_server.user.dto;

import com.knu.ntttt_server.user.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {

    private final Long id;
    private final String walletAddr;
    private final String nickname;
    private final String phoneNumber;
    private final String password;

    public UserDto(Long id, String walletAddr, String nickname, String phoneNumber, String password) {
        this.id = id;
        this.walletAddr = walletAddr;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .walletAddr(walletAddr)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .password(password)
                .build();
    }
}
