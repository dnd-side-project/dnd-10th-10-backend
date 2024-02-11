package com.dnd.api.domains.room.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.room.service.RoomService;
import com.dnd.api.domains.room.dto.CreateRoomRequest;
import com.dnd.api.domains.room.dto.EnterRoomRequest;
import com.dnd.api.domains.room.dto.RoomIdResponse;
import com.dnd.api.domains.room.dto.FindRoomByCodeResponse;
import com.dnd.api.domains.room.dto.FindRoomResponse;
import com.dnd.domain.room.entity.Room;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomController implements RoomApiPresentation {

	private final RoomService roomService;

	@PostMapping
	public ResponseEntity<ApiResult<RoomIdResponse>> createRoom(
			final @Valid @RequestBody CreateRoomRequest roomCreateRequestDto
	) {
		LocalDate registeredDate = LocalDate.now();
		Room createdRoom = roomService.createRoom(roomCreateRequestDto, registeredDate);
		RoomIdResponse responseDto = RoomIdResponse.from(createdRoom);
		return ResponseEntity.status(CREATED).body(ApiResult.ok(responseDto));
	}

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

	@PostMapping("/entrance")
	public ApiResult<RoomIdResponse> enterRoom(
			final @RequestBody @Valid EnterRoomRequest enterRoomRequestDto
	) {
		String inviteCode = enterRoomRequestDto.getInviteCode();
		Room room = roomService.enterRoom(inviteCode);
		return ApiResult.ok(RoomIdResponse.from(room));
	}
}
