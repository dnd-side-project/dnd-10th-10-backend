package com.dnd.api.domains.room.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.dnd.api.auth.LoginMember;
import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.room.dto.*;
import com.dnd.api.domains.room.service.RoomService;
import com.dnd.domain.member.entity.Member;
import com.dnd.domain.room.entity.Room;
import com.dnd.domain.vo.RestrictApp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomController implements RoomApiPresentation {

	private final RoomService roomService;

	@GetMapping("/{roomId}")
	public ApiResult<FindActiveRoomResponse> findActiveRoom(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		Room room = roomService.findRoom(roomId);
		FindActiveRoomResponse responseDto = FindActiveRoomResponse.from(room);
		return ApiResult.ok(responseDto);
	}

	@GetMapping
	public ApiResult<FindRoomByCodeResponse> findRoomByInviteCode(
			final @LoginMember Member member,
			final @RequestParam String inviteCode
	) {
		Room room = roomService.findRoomByInviteCode(inviteCode);
		FindRoomByCodeResponse responseDto = FindRoomByCodeResponse.from(room);
		return ApiResult.ok(responseDto);
	}

	@PostMapping
	public ResponseEntity<ApiResult<CreateRoomResponse>> createRoom(
			final @LoginMember Member member,
			final @Valid @RequestBody CreateRoomRequest roomCreateRequestDto
	) {
		Room createdRoom = roomService.createRoom(roomCreateRequestDto, member);
		return ResponseEntity.status(CREATED).body(ApiResult.ok(CreateRoomResponse.from(createdRoom)));
	}

	@PostMapping("/entrance")
	public ApiResult<RoomIdResponse> enterRoom(
			final @LoginMember Member member,
			final @RequestBody @Valid EnterRoomRequest enterRoomRequestDto
	) {
		String inviteCode = enterRoomRequestDto.getInviteCode();
		Room room = roomService.enterRoom(inviteCode, member);
		return ApiResult.ok(RoomIdResponse.from(room));
	}

	@PostMapping("/{roomId}/start")
	public ApiResult<RoomIdResponse> startRoom(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		Room room = roomService.startRoom(member, roomId);
		return ApiResult.ok(RoomIdResponse.from(room));
	}

	@GetMapping("/{roomId}/waiting")
	public ApiResult<FindWaitingRoomResponse> findWaitingRoom(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		FindWaitingRoomResponse responseDto = roomService.findWaitingRoom(roomId);
		return ApiResult.ok(responseDto);
	}

	@GetMapping("/active")
	public ApiResult<FindActiveRoomsResponse> findActiveRooms(
			final @LoginMember Member member,
			final @RequestParam(required = false) RestrictApp restrictApp
	) {
		FindActiveRoomsResponse responseDto = roomService.findActiveRooms(member, restrictApp);
		return ApiResult.ok(responseDto);
	}

	@GetMapping("/finished")
	public ApiResult<FindFinishedRoomsResponse> findFinishedRooms(
			final @LoginMember Member member
	) {
		return ApiResult.ok(FindFinishedRoomsResponse.from());
	}

	@GetMapping("/{roomId}/check-host")
	public ApiResult<CheckHostResponse> checkHost(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		boolean checkHost = roomService.checkHost(member, roomId);
		return ApiResult.ok(CheckHostResponse.from(checkHost));
	}

	@DeleteMapping("/{roomId}")
	public ApiResult<RoomIdResponse> deleteRoom(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		Room room = roomService.deleteRoom(member, roomId);
		return ApiResult.ok(RoomIdResponse.from(room));
	}
}
