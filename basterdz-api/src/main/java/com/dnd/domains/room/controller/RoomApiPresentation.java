package com.dnd.domains.room.controller;

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
	ResponseEntity<ApiResult<FindRoomResponseDto>> findRoom(final Long roomId);
}
