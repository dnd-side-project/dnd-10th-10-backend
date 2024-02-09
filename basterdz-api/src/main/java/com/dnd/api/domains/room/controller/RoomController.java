package com.dnd.api.domains.room.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.room.service.RoomService;
import com.dnd.api.domains.room.dto.request.CreateRoomRequestDto;
import com.dnd.api.domains.room.dto.request.EnterRoomRequestDto;
import com.dnd.api.domains.room.dto.response.RoomIdResponseDto;
import com.dnd.api.domains.room.dto.response.FindRoomByCodeResponseDto;
import com.dnd.api.domains.room.dto.response.FindRoomResponseDto;
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
	public ResponseEntity<ApiResult<RoomIdResponseDto>> createRoom(
			final @Valid @RequestBody CreateRoomRequestDto roomCreateRequestDto
	) {
		LocalDate registeredDate = LocalDate.now();
		Room createdRoom = roomService.createRoom(roomCreateRequestDto, registeredDate);
		RoomIdResponseDto responseDto = RoomIdResponseDto.from(createdRoom);
		return ResponseEntity.status(CREATED).body(ApiResult.ok(responseDto));
	}

	@GetMapping("/{roomId}")
	public ApiResult<FindRoomResponseDto> findRoom(
			final @PathVariable Long roomId
	) {
		Room room = roomService.findRoom(roomId);
		FindRoomResponseDto responseDto = FindRoomResponseDto.from(room);
		return ApiResult.ok(responseDto);
	}

	@GetMapping
	public ApiResult<FindRoomByCodeResponseDto> findRoomByInviteCode(
			final @RequestParam String inviteCode
	) {
		Room room = roomService.findRoomByInviteCode(inviteCode);
		FindRoomByCodeResponseDto responseDto = FindRoomByCodeResponseDto.from(room);
		return ApiResult.ok(responseDto);
	}

	@PostMapping("/entrance")
	public ApiResult<RoomIdResponseDto> enterRoom(
			final @RequestBody @Valid EnterRoomRequestDto enterRoomRequestDto
	) {
		String inviteCode = enterRoomRequestDto.getInviteCode();
		Room room = roomService.enterRoom(inviteCode);
		return ApiResult.ok(RoomIdResponseDto.from(room));
	}
}
