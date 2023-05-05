package com.knu.ntttt_server.token.controller;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.token.service.PaymentService;
import com.knu.ntttt_server.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Payment")
@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;

    /**
     * 결제 페이지에서 결제 버튼을 클릭하면 토큰을 구매할 수 있다.
     */
    @Operation(summary = "토큰 구매", description = "토큰 id(id)에 해당하는 토큰을 구매합니다. Request body의 Example Value: { \"id\": 1 }")
    @PostMapping("/payment")
    public ApiResponse<?> purchaseToken(@RequestBody Map<String, Long> param, Principal principal) {
        if (principal == null) {
            return ApiResponse.error(new KnuException(ResultCode.BAD_REQUEST, "로그인을 해주세요"));
        }
        String nickname = principal.getName();
        Long purchasedToken = paymentService.purchase(userService.getWalletAddress(nickname),
            param.get("id"));
        return ApiResponse.ok(purchasedToken);
    }
}
