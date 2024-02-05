package com.dnd.domains.room.controller;

import org.springframework.http.ResponseEntity;

import com.dnd.common.dto.ApiResult;
import com.dnd.domains.room.dto.request.RoomCreateRequestDto;
import com.dnd.domains.room.dto.response.RoomCreateResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Room", description = "Room API")
public interface RoomApiPresentation {

	@Operation(summary = "목표 방 생성")
	@ApiResponse(responseCode = "201", description = "방 생성 성공")
	ResponseEntity<ApiResult<RoomCreateResponseDto>> createRoom(final RoomCreateRequestDto roomCreateRequestDto);
}
