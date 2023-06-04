package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Group;
import java.util.List;

public interface ArtistService {
    Artist findBy(Long artistId);
    List<Artist> findAll();
    List<Artist> findAllBy(Group group, String name);
}
