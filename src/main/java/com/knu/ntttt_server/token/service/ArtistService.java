package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Group;
import java.util.List;

public interface ArtistService {
    Artist queryBy(Long artistId);
    List<Artist> queryAll();
    List<Artist> queryAllBy(Group group);
}
