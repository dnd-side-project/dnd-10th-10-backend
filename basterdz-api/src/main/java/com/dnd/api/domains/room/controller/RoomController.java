package com.dnd.api.domains.room.controller;

import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.room.service.RoomService;
import com.dnd.api.domains.room.dto.FindRoomByCodeResponse;
import com.dnd.api.domains.room.dto.FindRoomResponse;
import com.dnd.domain.room.entity.Room;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomController implements RoomApiPresentation {

	private final RoomService roomService;

	@GetMapping("/{roomId}")
	public ApiResult<FindRoomResponse> findRoom(
			final @PathVariable Long roomId
	) {
		Room room = roomService.findRoom(roomId);
		FindRoomResponse responseDto = FindRoomResponse.from(room);
		return ApiResult.ok(responseDto);
	}

	@GetMapping
	public ApiResult<FindRoomByCodeResponse> findRoomByInviteCode(
			final @RequestParam String inviteCode
	) {
		Room room = roomService.findRoomByInviteCode(inviteCode);
		FindRoomByCodeResponse responseDto = FindRoomByCodeResponse.from(room);
		return ApiResult.ok(responseDto);
	}
}
