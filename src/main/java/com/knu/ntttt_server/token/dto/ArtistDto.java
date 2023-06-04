package com.knu.ntttt_server.token.dto;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Group;
import lombok.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistDto {

    @Builder
    public record ArtistRes(Long id, Group group, String name, String imgUrl, String ratio) {
        public static ArtistRes from(Artist artist) {
            return ArtistRes.builder()
                    .id(artist.getId())
                    .group(artist.getGroup())
                    .name(artist.getName())
                    .imgUrl(artist.getImgUrl())
                    .ratio(artist.getRatio())
                    .build();
        }
    }

    public record ArtistsByGroup(Group group, List<Artist> members) {
        public static List<ArtistsByGroup> distribute(List<Artist> artists) {
            Map<Group, List<Artist>> map = new HashMap<>();
            for (Artist artist : artists) {
                Group g = artist.getGroup();
                if (!map.containsKey(g)) {
                    map.put(g, new ArrayList<>());
                }
                map.get(g).add(artist);
            }

            List<ArtistsByGroup> artistsByGroups = new ArrayList<>();
            for (Group g : map.keySet()) {
                artistsByGroups.add(new ArtistsByGroup(g, map.get(g)));
            }
            return artistsByGroups;
        }
    }
}
