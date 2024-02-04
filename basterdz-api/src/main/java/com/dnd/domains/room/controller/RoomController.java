package com.dnd.domains.room.controller;

import com.dnd.common.dto.ApiResult;
import com.dnd.domains.room.dto.request.RoomCreateRequestDto;
import com.dnd.domains.room.dto.response.RoomCreateResponseDto;
import com.dnd.domains.room.service.RoomService;
import com.dnd.room.Room;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController implements RoomApiPresentation {

	private final RoomService roomService;

	@PostMapping
	public ApiResult<RoomCreateResponseDto> createRoom(
		final @Valid @RequestBody RoomCreateRequestDto roomCreateRequestDto
	) {
		Room createdRoom = roomService.createRoom(roomCreateRequestDto);
		RoomCreateResponseDto responseDto = RoomCreateResponseDto.from(createdRoom);
		return ApiResult.ok(responseDto);
	}
}
