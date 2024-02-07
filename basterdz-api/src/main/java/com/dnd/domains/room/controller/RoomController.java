package com.dnd.domains.room.controller;

import com.dnd.common.dto.ApiResult;
import com.dnd.domains.room.dto.request.CreateRoomRequestDto;
import com.dnd.domains.room.dto.request.EnterRoomRequestDto;
import com.dnd.domains.room.dto.response.CreateRoomResponseDto;
import com.dnd.domains.room.dto.response.FindRoomByCodeResponseDto;
import com.dnd.domains.room.dto.response.FindRoomResponseDto;
import com.dnd.domains.room.service.RoomService;
import com.dnd.room.Room;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomController implements RoomApiPresentation {

	private final RoomService roomService;

	@PostMapping
	public ResponseEntity<ApiResult<CreateRoomResponseDto>> createRoom(
			final @Valid @RequestBody CreateRoomRequestDto roomCreateRequestDto
	) {
		LocalDate registeredDate = LocalDate.now();
		Room createdRoom = roomService.createRoom(roomCreateRequestDto, registeredDate);
		CreateRoomResponseDto responseDto = CreateRoomResponseDto.from(createdRoom);
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

	@PostMapping("{roomId}/entrance")
	public ResponseEntity<ApiResult<Void>> enterRoom(
			final @PathVariable Long roomId,
			final @RequestBody @Valid EnterRoomRequestDto enterRoomRequestDto
	) {
		String inviteCode = enterRoomRequestDto.getInviteCode();
		roomService.enterRoom(roomId, inviteCode);
		return ResponseEntity.status(CREATED).body(ApiResult.ok(null));
	}
}
