package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.token.dto.GachaDto;
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

    /**
     * 유저의 가챠 토큰을 가져옵니다
     * 만약 오늘 가차토큰을 돌리지 않았다면 가챠를 한번 돌린 후 데이터를 가져옵니다.
     * @param username
     * @return
     */
    @Override
    @Transactional
    public GachaDto.GachaRes getGachaToken(String username) {
        User user = userRepository.findByNickname(username).orElseThrow(() -> new KnuException("유저가 존재하지 않습니다"));
        Optional<UserGachaToken> userGachaTokenOptional = userGachaTokenRepository.findByUserAndDate(user, LocalDate.now());

        if (userGachaTokenOptional.isEmpty()) {
            return playGacha(username);
        }

        UserGachaToken userGachaToken = userGachaTokenOptional.get();

        if (userGachaToken.getToken().getPaymentState().equals(PaymentState.PENDING)) {
            throw new KnuException(ResultCode.TOKEN_NOT_FOUND);
        }
        return new GachaDto.GachaRes(new TokenDto.TokenRes(userGachaTokenOptional.get().getToken()), userGachaToken.getChance());
    }

    /**
     *
     * 가챠를 플레이합니다
     * 유저는 오늘 가챠를 할 기회가 남아있다면 유저가 선택한 아티스트의 토큰 중 하나를 볼 수 있습니다.
     * 가챠를 플레이할 때마다 기회는 감소합니다.
     * @param username: 유저의 닉네임
     * @return tokenRes: 해당 가챠 랜덤 토큰 정보
     */
    @Override
    @Transactional
    public GachaDto.GachaRes playGacha(String username) {
        log.info("[Gacha]: " + username + " play Gacha");

        User user = userRepository.findByNickname(username).orElseThrow(() -> new KnuException("유저가 존재하지 않습니다"));
        UserGachaToken todaysUserGachaToken = userGachaTokenRepository.findByUserAndDate(user, LocalDate.now())
                .orElse(new UserGachaToken(user, Token.builder().build()));
        validateChanceCountOver(todaysUserGachaToken);
        todaysUserGachaToken.playGacha(getRandomToken(user));

        log.info("[Gacha]: " + username + " get Gacha token id: {}, chance: ",
                todaysUserGachaToken.getToken().getId(), todaysUserGachaToken.getChance());

        userGachaTokenRepository.save(todaysUserGachaToken);
        return new GachaDto.GachaRes(new TokenDto.TokenRes(todaysUserGachaToken.getToken()), todaysUserGachaToken.getChance());
    }

    private void validateChanceCountOver(UserGachaToken todaysUserGachaToken) {
        if (todaysUserGachaToken.getChance() <= 0) {
            throw new KnuException(ResultCode.GACHA_CHANCE_OVER);
        }
    }

    private Token getRandomToken(User user) {

        if (!userArtistRepository.existsByUserId(user.getId())) {
            throw new KnuException("유저의 아티스트 선택 정보가 없습니다.");
        }

        return tokenRepository.findRandomTokenByUserIdAndPaymentStateOnSale(user.getId()).
                orElseThrow(() -> new KnuException(ResultCode.TOKEN_NOT_FOUND));
    }
}
