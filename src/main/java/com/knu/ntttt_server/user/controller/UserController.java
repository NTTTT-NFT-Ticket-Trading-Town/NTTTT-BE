package com.knu.ntttt_server.user.controller;

import com.knu.ntttt_server.core.annotation.CurrentUser;
import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.token.dto.TokenDto.QueryTokenRes;
import com.knu.ntttt_server.token.service.TokenService;
import com.knu.ntttt_server.user.dto.UserArtistDto.ChooseArtistReq;
import com.knu.ntttt_server.user.dto.LoginDto;
import com.knu.ntttt_server.user.dto.UserArtistDto.ChosenArtistRes;
import com.knu.ntttt_server.user.dto.UserDto;
import com.knu.ntttt_server.user.service.UserArtistService;
import com.knu.ntttt_server.user.service.UserPageService;
import com.knu.ntttt_server.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserArtistService userArtistService;
    private final UserPageService userPageService;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ApiResponse<?> join(@RequestBody UserDto dto) {
        userService.join(dto);
        return ApiResponse.ok();
    }

    @Operation(summary = "로그인")
    @GetMapping("/login")
    public ApiResponse<?> login(@RequestBody LoginDto dto) {
        String token = userService.login(dto);
        return ApiResponse.ok(token);
    }

    @Operation(summary = "토큰 health check")
    @GetMapping("/token")
    public ApiResponse<?> token(@CurrentUser User user) {
        if (user != null) {
            return ApiResponse.ok(user.getUsername());
        }

        return ApiResponse.ok();
    }

    @Operation(summary = "아티스트 선택")
    @PostMapping("/artist")
    public ApiResponse<?> choose(@RequestBody List<ChooseArtistReq> artistList, @CurrentUser User user) {
        if (user == null) {
            return ApiResponse.error(new KnuException(ResultCode.BAD_REQUEST, "로그인을 해주세요"));
        }
        userArtistService.chooseArtist(artistList, user.getUsername());
        return ApiResponse.ok();
    }

    @Operation(summary = "사용자 정보 조회", description = "사용자가 소유한 모든 토큰과, 선택한 아티스트를 조회합니다.")
    @GetMapping({"/mypage", "/{nickname}"})
    public ApiResponse<?> getInfo(@CurrentUser User user, @PathVariable(required = false) String nickname) {
        if (nickname != null) {
            return ApiResponse.ok(userPageService.getUserInfo(nickname));
        }
        else if (user != null) {
            return ApiResponse.ok(userPageService.getUserInfo(user.getUsername()));
        }
        else {
            return ApiResponse.error(new KnuException(ResultCode.BAD_REQUEST, "로그인을 해주세요"));
        }
    }
}
