package com.knu.ntttt_server.token.dto;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistDto {
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
