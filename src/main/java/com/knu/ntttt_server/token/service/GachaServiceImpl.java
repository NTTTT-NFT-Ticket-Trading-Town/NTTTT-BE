package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.token.dto.TokenDto;
import com.knu.ntttt_server.token.model.PaymentState;
import com.knu.ntttt_server.token.model.Token;
import com.knu.ntttt_server.token.model.UserGachaToken;
import com.knu.ntttt_server.token.repository.TokenRepository;
import com.knu.ntttt_server.token.repository.UserGachaTokenRepository;
import com.knu.ntttt_server.user.model.User;
import com.knu.ntttt_server.user.model.UserArtist;
import com.knu.ntttt_server.user.repository.UserArtistRepository;
import com.knu.ntttt_server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GachaServiceImpl implements GachaService {
    private final UserRepository userRepository;
    private final UserArtistRepository userArtistRepository;
    private final UserGachaTokenRepository userGachaTokenRepository;
    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public TokenDto.TokenRes getGachaToken(String username) {
        User user = userRepository.findByNickname(username).orElseThrow(() -> new KnuException("유저가 존재하지 않습니다"));
        Optional<UserGachaToken> userGachaTokenOptional = userGachaTokenRepository.findByUserAndDate(user, LocalDate.now());

        if (userGachaTokenOptional.isEmpty()) {
            return playGacha(username);
        }

        UserGachaToken userGachaToken = userGachaTokenOptional.get();

        if (userGachaToken.getToken().getPaymentState().equals(PaymentState.SOLD_OUT)) {
            throw new KnuException(ResultCode.TOKEN_SOLD_OUT);
        }
        if (userGachaToken.getToken().getPaymentState().equals(PaymentState.PENDING)) {
            throw new KnuException(ResultCode.TOKEN_NOT_FOUND);
        }
        return new TokenDto.TokenRes(userGachaTokenOptional.get().getToken());
    }

    @Override
    @Transactional
    public TokenDto.TokenRes playGacha(String username) {
        log.info("[Gacha]: " + username + " play Gacha");

        User user = userRepository.findByNickname(username).orElseThrow(() -> new KnuException("유저가 존재하지 않습니다"));
        UserGachaToken todaysUserGachaToken = userGachaTokenRepository.findByUserAndDate(user, LocalDate.now())
                .orElse(new UserGachaToken(user, Token.builder().build()));
        validateChanceCountOver(todaysUserGachaToken);
        todaysUserGachaToken.playGacha(getRandomToken(user));

        log.info("[Gacha]: " + username + " get Gacha token id: {}, chance: ",
                todaysUserGachaToken.getToken().getId(), todaysUserGachaToken.getChance());

        userGachaTokenRepository.save(todaysUserGachaToken);
        return new TokenDto.TokenRes(todaysUserGachaToken.getToken());
    }

    private void validateChanceCountOver(UserGachaToken todaysUserGachaToken) {
        if (todaysUserGachaToken.getChance() <= 0) {
            throw new KnuException(ResultCode.GACHA_CHANCE_OVER);
        }
    }

    private Token getRandomToken(User user) {
        UserArtist randomUserArtist = userArtistRepository.findRandomUserArtistByUserId(user.getId()).
                orElseThrow(() -> new KnuException("유저의 아티스트 선택 정보가 없습니다."));
        return tokenRepository.findRandomTokenByArtistIdAndPaymentStateOnSale(randomUserArtist.getId()).
                orElseThrow(() -> new KnuException(ResultCode.TOKEN_NOT_FOUND));
    }
}
