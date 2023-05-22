package com.knu.ntttt_server.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GachaDto {

    public record GachaRes(
            @JsonProperty("token") TokenDto.TokenRes tokenDto,
            int chance) {

    }
}
