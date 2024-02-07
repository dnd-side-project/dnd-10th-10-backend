package com.dnd.domains.room.controller;

import com.dnd.domains.room.dto.request.EnterRoomRequestDto;
import com.dnd.domains.room.dto.response.FindRoomByCodeResponseDto;
import com.dnd.domains.room.dto.response.FindRoomResponseDto;
import org.springframework.http.ResponseEntity;

import com.dnd.common.dto.ApiResult;
import com.dnd.domains.room.dto.request.CreateRoomRequestDto;
import com.dnd.domains.room.dto.response.CreateRoomResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Room", description = "Room API")
public interface RoomApiPresentation {

	@Operation(summary = "목표 방 생성")
	@ApiResponse(responseCode = "201", description = "방 생성 성공")
	ResponseEntity<ApiResult<CreateRoomResponseDto>> createRoom(final CreateRoomRequestDto roomCreateRequestDto);

	@Operation(summary = "목표 방 조회")
	@ApiResponse(responseCode = "200", description = "방 조회 성공")
	ApiResult<FindRoomResponseDto> findRoom(final Long roomId);

	@Operation(summary = "초대 코드를 통한 목표 방 조회")
	@ApiResponse(responseCode = "200", description = "방 조회 성공")
	ApiResult<FindRoomByCodeResponseDto> findRoomByInviteCode(final String inviteCode);

	@Operation(summary = "목표 방 입장")
	@ApiResponse(responseCode = "201", description = "방 입장 성공")
	ResponseEntity<ApiResult<Void>> enterRoom(final Long roomId, final EnterRoomRequestDto enterRoomRequestDto);
}
