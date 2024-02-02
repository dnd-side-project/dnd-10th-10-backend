package com.dnd.domains.room.controller;

import com.dnd.common.dto.ApiResult;
import com.dnd.domains.room.dto.request.RoomCreateRequestDto;
import com.dnd.domains.room.dto.response.RoomCreateResponseDto;
import com.dnd.domains.room.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/rooms")
    public ApiResult<RoomCreateResponseDto> createRoom(
            final @Valid @RequestBody RoomCreateRequestDto roomCreateRequestDto
    ) {
        RoomCreateResponseDto room = roomService.createRoom(roomCreateRequestDto.toEntity());
        return ApiResult.ok(room);
    }
}
