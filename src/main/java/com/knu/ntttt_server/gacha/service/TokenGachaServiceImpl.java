package com.knu.ntttt_server.gacha.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.gacha.repository.TokenGachaRepository;
import com.knu.ntttt_server.nft.service.NftService;
import com.knu.ntttt_server.token.model.PaymentState;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import com.knu.ntttt_server.token.model.Token;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenGachaServiceImpl implements TokenGachaService{
    private final TokenGachaRepository tokenGachaRepository;
    private final NftService nftService;
    private final Random random = new Random();

    /**
     * 1. 판매 중인 토큰 중에
     * 2. 유저가 뽑을 수 있는 토큰을 조회한다
     *  - 금일 유저가 조회하지 않은 토큰
     */
    @Override
    public Token gacha(Long userId) {
        if (!hasCredit(userId)) {
            throw new KnuException("가챠 횟수가 소진되었습니다");
        }

        List<Token> tokenOnSales = getTokensOnSale();

        while (tokenOnSales.size() > 0) {
            int randomIndex = random.nextInt(tokenOnSales.size());
            Token token = tokenOnSales.get(randomIndex);
            if (canChoose(userId, token)) {
                return token;
            }
            tokenOnSales.remove(token);
        }
        throw new KnuException("뽑을 수 있는 토큰이 없습니다");
    }

    // TODO("유저가 토큰을 뽑을 수 있는 횟수가 있는지 확인한다")
    private boolean hasCredit(Long userId) {
        return true;
    }

    // TODO("유저가 토큰을 뽑을 수 있는 토큰인지 확인한다")
    private boolean canChoose(Long userId, Token token) {
        return true;
    }

    private List<Token> getTokensOnSale() {
        return tokenGachaRepository.findTokensByPaymentState(PaymentState.ON_SALE);
    }
}


