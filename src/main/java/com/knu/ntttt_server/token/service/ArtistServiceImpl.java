package com.knu.ntttt_server.token.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.token.model.Artist;
import com.knu.ntttt_server.token.model.Group;
import com.knu.ntttt_server.token.repository.ArtistRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService{
    private final ArtistRepository artistRepository;

    @Override
    public Artist findBy(Long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new KnuException("존재하지 않는 아티스트입니다"));
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public List<Artist> findAllBy(Group group, String name) {
        if (group == null && name == null) {
            throw new KnuException("입력값이 잘못되었습니다");
        }

        if (group == null) {
            return artistRepository.findAllByNameContaining(name);
        } else if (name == null) {
            return artistRepository.findAllByGroup(group);
        }
        return artistRepository.findAllByGroupAndNameContaining(group, name);
    }
}
