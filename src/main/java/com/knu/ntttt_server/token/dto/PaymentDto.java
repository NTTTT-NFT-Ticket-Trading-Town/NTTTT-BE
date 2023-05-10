package com.knu.ntttt_server.token.dto;

import lombok.Getter;

@Getter
public class PaymentDto {
    public record TokenPurchaseReq(Long tokenId) { }
}
