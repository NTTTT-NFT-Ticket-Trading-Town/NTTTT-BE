package com.knu.ntttt_server.user.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.token.dto.TokenDto.TokenRes;
import com.knu.ntttt_server.token.service.TokenService;
import com.knu.ntttt_server.user.dto.UserArtistDto.ChosenArtistRes;
import com.knu.ntttt_server.user.repository.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPageService {

    private final TokenService tokenService;
    private final UserArtistService userArtistService;
    private final UserRepository userRepository;

    public Map<String, Object> getTokenAndArtistBy(String nickname) {
        if (!userRepository.existsByNickname(nickname)) {
            throw new KnuException(ResultCode.BAD_REQUEST, "해당 닉네임의 유저가 존재하지 않습니다.");
        }
        List<TokenRes> gachaList = tokenService.findAllTokenOwnedBy(nickname);
        List<ChosenArtistRes> categoryList = userArtistService.findChosenArtist(nickname);
        Map<String, Object> data = new HashMap<>();
        data.put("gacha_list", gachaList);
        data.put("category_list", categoryList);
        return data;
    }
}
