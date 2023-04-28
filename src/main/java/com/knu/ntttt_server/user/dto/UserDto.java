package com.knu.ntttt_server.user.dto;

import com.knu.ntttt_server.user.model.User;
import lombok.Getter;

@Getter
public class UserDto {

    private final Long id;
    private final String walletAddr;
    private final String nickname;
    private final String phoneNumber;

    public UserDto(Long id, String walletAddr, String nickname, String phoneNumber) {
        this.id = id;
        this.walletAddr = walletAddr;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .walletAddr(walletAddr)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .build();
    }
}
