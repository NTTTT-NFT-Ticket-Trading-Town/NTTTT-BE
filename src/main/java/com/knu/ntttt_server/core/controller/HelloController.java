package com.knu.ntttt_server.core.controller;

import com.knu.ntttt_server.core.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test")
@RestController()
@RequestMapping("/hello")
public class HelloController {

    @Operation(summary = "health check", description = "health check용 api")
    @GetMapping
    public ApiResponse<?> hello() {
        return ApiResponse.ok("hi");
    }
}
