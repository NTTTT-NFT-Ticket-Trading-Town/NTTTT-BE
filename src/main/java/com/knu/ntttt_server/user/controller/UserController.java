package com.knu.ntttt_server.user.controller;

import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.user.dto.LoginDto;
import com.knu.ntttt_server.user.dto.UserDto;
import com.knu.ntttt_server.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
}
