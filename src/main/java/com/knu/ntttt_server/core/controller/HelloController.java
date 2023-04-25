package com.knu.ntttt_server.core.controller;

import com.knu.ntttt_server.core.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ApiResponse<?> hello() {
        return ApiResponse.ok("hi");
    }
}
