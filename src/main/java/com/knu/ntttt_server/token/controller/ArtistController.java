package com.knu.ntttt_server.token.controller;

import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.token.dto.ArtistDto.ArtistsByGroup;
import com.knu.ntttt_server.token.model.Group;
import com.knu.ntttt_server.token.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @Operation(summary = "모든 아티스트 조회", description = "모든 아티스트를 그룹별로 조회합니다.")
    @GetMapping("/artist/all")
    public ApiResponse<List<ArtistsByGroup>> findAllArtists() {
        List<ArtistsByGroup> res = ArtistsByGroup.distribute(artistService.findAll());
        return ApiResponse.ok(res);
    }

    @Operation(summary = "그룹 내 아티스트 조회", description = "그룹 내 아티스트를 조회합니다.")
    @GetMapping("/artist/{group}")
    public ApiResponse<ArtistsByGroup> findArtistsInGroup(@PathVariable Group group) {
        ArtistsByGroup res = new ArtistsByGroup(group, artistService.findAllBy(group));
        return ApiResponse.ok(res);
    }
}
