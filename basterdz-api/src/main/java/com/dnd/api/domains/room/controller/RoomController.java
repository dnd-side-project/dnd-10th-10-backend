package com.dnd.api.domains.room.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.dnd.api.auth.LoginMember;
import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.room.dto.*;
import com.dnd.api.domains.room.service.RoomService;
import com.dnd.domain.member.entity.Member;
import com.dnd.domain.room.entity.Room;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomController implements RoomApiPresentation {

	private final RoomService roomService;

	@GetMapping("/{roomId}")
	public ApiResult<FindActiveRoomResponse> findActiveRoom(
			final @PathVariable Long roomId
	) {
		Room room = roomService.findRoom(roomId);
		FindActiveRoomResponse responseDto = FindActiveRoomResponse.from(room);
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

	@PostMapping
	public ResponseEntity<ApiResult<RoomIdResponse>> createRoom(
			final @LoginMember Member member,
			final @Valid @RequestBody CreateRoomRequest roomCreateRequestDto
	) {
		LocalDate registeredDate = LocalDate.now();
		Room createdRoom = roomService.createRoom(roomCreateRequestDto, registeredDate, member);
		RoomIdResponse responseDto = RoomIdResponse.from(createdRoom);
		return ResponseEntity.status(CREATED).body(ApiResult.ok(responseDto));
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

	@PostMapping("/{roomId}/members")
	public ApiResult<RoomIdResponse> startRoom(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		Room room = roomService.startRoom(member, roomId);
		return ApiResult.ok(RoomIdResponse.from(room));
	}

	@GetMapping("/{roomId}/members")
	public ApiResult<FindWaitingRoomResponse> findWaitingRoom(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		Room room = roomService.findRoom(roomId);
		List<RoomMemberResponse> roomMemberResponses = roomService.findWaitingRoom(room);
		return ApiResult.ok(FindWaitingRoomResponse
				.createFindWaitingRoomResponse(room, roomMemberResponses));
	}

	@GetMapping("/{roomId}/active")
	public ApiResult<FindActiveRoomsResponse> findActiveRooms(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		return ApiResult.ok(FindActiveRoomsResponse.from());
	}

	@GetMapping("/{roomId}/finished")
	public ApiResult<FindFinishedRoomsResponse> findFinishedRooms(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		return ApiResult.ok(FindFinishedRoomsResponse.from());
	}

	@GetMapping("/{roomId}/check-host")
	public ApiResult<CheckHostResponse> checkHost(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		return ApiResult.ok(CheckHostResponse.from());
	}

	@DeleteMapping("/{roomId}")
	public ApiResult<RoomIdResponse> deleteRoom(
			final @LoginMember Member member,
			final @PathVariable Long roomId
	) {
		return ApiResult.ok(RoomIdResponse.temp());
	}
}
