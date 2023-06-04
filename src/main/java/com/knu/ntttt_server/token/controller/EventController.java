package com.knu.ntttt_server.token.controller;

import com.knu.ntttt_server.core.response.ApiResponse;
import com.knu.ntttt_server.token.model.Event;
import com.knu.ntttt_server.token.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class EventController {
    private final EventService eventService;

    @Operation(summary = "모든 이벤트 조회", description = "모든 이벤트(콜렉션)를 조회합니다.")
    @GetMapping("/event/all")
    public ApiResponse<List<Event>> findAllEvents() {
        return ApiResponse.ok(eventService.findAll());
    }

}
