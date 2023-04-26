package com.knu.ntttt_server.nft.service;

import com.knu.ntttt_server.nft.dto.NftDto.CreateNftReq;
import com.knu.ntttt_server.nft.dto.NftDto.QueryNftRes;

public interface NftService {
    Long mintNft(CreateNftReq req);
    void transferNft(String to, Long nftId);
    QueryNftRes queryNft(Long nftId);
}
