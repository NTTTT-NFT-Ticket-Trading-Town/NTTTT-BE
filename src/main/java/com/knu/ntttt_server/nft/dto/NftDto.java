package com.knu.ntttt_server.nft.dto;

import java.util.Map;
import java.util.HashMap;

public class NftDto {
    public record CreateNftReq(String imgUrl, String desc) {
        public Map<String, String> payload() {
                Map<String, String> map = new HashMap<>();
                map.put("imgUrl", this.imgUrl);
                map.put("desc", this.desc);
                return map;
            }
    }

    public record QueryNftRes(Long id, String owner, String imgUrl, String desc) {
        public static QueryNftRes from(Long id, String owner, Map<String, String> payloads) {
            return new QueryNftRes(
                    id,
                    owner,
                    payloads.get("desc"),
                    payloads.get("imgUrl")
            );
        }
    }
}
