package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.nft.dto.NftDto.CreateNftReq;
import com.knu.ntttt_server.nft.dto.NftDto.QueryNftRes;
import com.knu.ntttt_server.nft.service.NftService;
import com.knu.ntttt_server.token.dto.TokenDto.TokenReq;
import com.knu.ntttt_server.token.dto.TokenDto.QueryTokenRes;
import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.token.model.Token;
import com.knu.ntttt_server.token.repository.TokenRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final EventService eventService;
    private final ArtistService artistService;
    private final NftService nftService;

    private final TokenRepository tokenRepository;
    @Value("${nft.contract.owner}")
    String ownerAddress;

    /**
     * 특정 이벤트의 토큰을 발행한다
     */
    @Transactional
    public Token createToken(TokenReq req) {
        Event event = eventService.findBy(req.eventId());
        Artist artist = artistService.findBy(req.artistId());
        Long nftId = issueNft(req);
        Token token = req.issueToken(getSequence(event), artist, event, nftId, ownerAddress);
        return tokenRepository.save(token);
    }

    /**
     * 서버 DB 에 저장된 토큰 메타데이터 + 블록체인에 저장된 NFT 데이터를 반환
     */
    @Override
    @Transactional
    public QueryTokenRes findBy(Long tokenId) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 토큰입니다"));
        QueryNftRes res = nftService.queryNft(token.getNftId());
        this.syncTokenWithNft(res, token);
        return new QueryTokenRes(token, res.owner());
    }

    @Override
    public List<QueryTokenRes> findAllBy(Long eventId) {
        List<QueryTokenRes> res = new ArrayList<>();

        List<Token> tokens = tokenRepository.queryAllByEvent_Id(eventId);
        for (Token t : tokens) {
            res.add(findBy(t.getId()));
        }
        return res;
    }


    //이벤트의 가장 최신 시퀀스 번호를 불러온다
    //TODO(토큰 발행 시퀀스 관리 로직 리팩토링)
    private Integer getSequence(Event event) {
        return event.getQuantity();
    }

    private Long issueNft(TokenReq req) {
        CreateNftReq nftReq = new CreateNftReq(req.imgUrl(), req.desc());
        return nftService.mintNft(nftReq);
    }

    // 블록체인에 저장된 소유자 지갑 주소와 데이터베이스에 저장된 소유자 지갑 주소가 다른 경우 데이터베이스 갱신
    private void syncTokenWithNft(QueryNftRes nft, Token token) {
        token.changeOwner(nft.owner());
    }
}
