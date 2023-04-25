package com.knu.ntttt_server.nft.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knu.ntttt_server.nft.ContractUtils;
import com.knu.ntttt_server.nft.dto.NftDto.CreateNftReq;
import com.knu.ntttt_server.nft.dto.NftDto.QueryNftRes;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Slf4j
@Service
public class NftServiceImpl implements NftService {
    private final ContractUtils contractUtils;
    private final ObjectMapper mapper;

    private final String ownerAddress;

    @Autowired
    public NftServiceImpl(@Value("${nft.server}") String serverURL, @Value("${nft.contract.owner}") String ownerAddress,
                          @Value("${nft.contract.address}") String contractAddress, ObjectMapper mapper) {
        this.mapper = mapper;
        this.ownerAddress = ownerAddress;
        contractUtils = new ContractUtils(serverURL, ownerAddress, contractAddress);
    }

    @Override
    public Long mintNft(CreateNftReq req)  {
        Function f = createMintNftFunction(this.ownerAddress, req.payload());
        String txHash = contractUtils.sendTransaction(f);
        return getNftIdByTxHash(txHash, f);
    }

    @Override
    public void transferNft(String to, Long nftId) {
        Function f = createTransferNftFunction(this.ownerAddress, to, BigInteger.valueOf(nftId));
        String txHash = contractUtils.sendTransaction(f);
        if (txHash == null) {
            throw new RuntimeException("Transfer Failed");
        }
    }

    @Override
    public QueryNftRes queryNft(Long nftId) {
        BigInteger bi = BigInteger.valueOf(nftId);
        Function queryPayloadFunction = createQueryNftFunction(bi);
        Function queryOwnerFunction = createQueryOwnerFunction(bi);

        String callRes = contractUtils.call(queryPayloadFunction).get(0).toString();
        Map<String, String> payload;
        try {
            payload = mapper.readValue(callRes, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON Parsing Error");
        }
        String owner = contractUtils.call(queryOwnerFunction).get(0).toString();
        return QueryNftRes.from(nftId, owner, payload);
    }

    /**
     * 생성된 Transaction의 로그 정보를 바탕으로 NFT의 ID를 추출
     */
    private Long getNftIdByTxHash(String txHash, Function f) {
        List<Type> res = getMintNftResultByTxHash(txHash, f.getOutputParameters());
        return ((Uint256) res.get(0)).getValue().longValue();
    }

    /**
     * 생성된 Transaciton의 로그 정보를 바탕으로 필요한 정보를 추출
     * openzeppelin ERC721의 mintNft 트랜잭션 로그를 참조
     */
    private List<Type> getMintNftResultByTxHash(String txHash, List<TypeReference<Type>> outputParameters) {
        TransactionReceipt receipt = contractUtils.getReceipt(txHash);
        Log log = receipt.getLogs().get(0);
        String output = log.getTopics().get(3);
        return FunctionReturnDecoder.decode(output, outputParameters);
    }

    private Function createMintNftFunction(String recipient, Map<String, String> payload) {
        String jsonPayload;
        try {
            jsonPayload = mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON Parsing Error");
        }
        return new Function(
                "mintNFT",
                Arrays.asList(new Address(recipient), new Utf8String(jsonPayload)),
                List.of(new TypeReference<Uint256>() {
                }));
    }

    private Function createQueryOwnerFunction(BigInteger tokenId) {
        return new Function("ownerOf",
                List.of(new Uint256(tokenId)),
                List.of(new TypeReference<Address>() {
                }));
    }

    private Function createQueryNftFunction(BigInteger tokenId) {
        return new Function("tokenURI",
                List.of(new Uint256(tokenId)),
                List.of(new TypeReference<Utf8String>() {
                }));
    }

    private Function createTransferNftFunction(String from, String to, BigInteger nftId) {
        return new Function(
                "transferFrom",
                Arrays.asList(new Address(from), new Address(to), new Uint256(nftId)),
                List.of(new TypeReference<Uint256>() {
                }));
    }
}
