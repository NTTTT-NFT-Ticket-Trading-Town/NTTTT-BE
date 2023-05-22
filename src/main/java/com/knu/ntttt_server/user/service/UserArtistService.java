package com.knu.ntttt_server.user.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.service.ArtistService;
import com.knu.ntttt_server.user.dto.UserArtistDto.ChooseArtistReq;
import com.knu.ntttt_server.user.dto.UserArtistDto.ChosenArtistRes;
import com.knu.ntttt_server.user.dto.UserArtistDto.UserArtistReq;
import com.knu.ntttt_server.user.model.User;
import com.knu.ntttt_server.user.model.UserArtist;
import com.knu.ntttt_server.user.repository.UserArtistRepository;
import com.knu.ntttt_server.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserArtistService {

    private final UserRepository userRepository;
    private final UserArtistRepository userArtistRepository;
    private final ArtistService artistService;

    /**
     * user가 선호하는 artist를 선택하는 기능입니다.
     */
    public void chooseArtist(List<ChooseArtistReq> artistIdList, String nickname) {
        User user = userRepository.findByNickname(nickname)
            .orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST, "해당 닉네임의 유저를 찾을 수 없습니다"));
        List<UserArtist> userArtistList = new ArrayList<>();
        for (ChooseArtistReq chooseArtistReq : artistIdList) {
            Artist artist = artistService.findBy(chooseArtistReq.artistId());

            if (userArtistRepository.existsByArtistId(artist.getId())) {
                throw new KnuException(ResultCode.ARTIST_ALREADY_SELECT);
            }
            userArtistList.add(new UserArtistReq(user, artist).toEntity());
        }
        userArtistRepository.saveAll(userArtistList);
    }

    /**
     * user가 선택한 artist의 이름 조회
     */
    public List<ChosenArtistRes> findChosenArtist(String nickname) {
        User user = userRepository.findByNickname(nickname)
            .orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST, "해당 닉네임의 유저가 존재하지 않습니다."));
        List<UserArtist> userArtistList = userArtistRepository.findAllByUserId(user.getId());

        List<ChosenArtistRes> artistResList = new ArrayList<>();
        for (UserArtist userArtist : userArtistList) {
            artistResList.add(new ChosenArtistRes(userArtist.getArtist()));
        }
        return artistResList;
    }
}
