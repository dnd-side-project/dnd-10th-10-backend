package com.dnd.api.domains.room.controller;

import com.dnd.api.domains.room.dto.request.CreateRoomRequestDto;
import com.dnd.api.domains.room.dto.request.EnterRoomRequestDto;
import com.dnd.api.domains.room.dto.response.FindRoomResponseDto;
import com.dnd.api.domains.room.dto.response.FindRoomByCodeResponseDto;

import org.springframework.http.ResponseEntity;

import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.room.dto.response.RoomIdResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "\uD83C\uDFE0Room", description = "Room API")
public interface RoomApiPresentation {

	@Operation(summary = "목표 방 생성")
	@ApiResponse(responseCode = "201", description = "방 생성 성공")
	ResponseEntity<ApiResult<RoomIdResponseDto>> createRoom(final CreateRoomRequestDto roomCreateRequestDto);

	@Operation(summary = "목표 방 조회")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "방 조회 성공"),
			@ApiResponse(responseCode = "404", description = "존재하지 않는 그룹",
				content = @Content(schema = @Schema(
					example = "{\"success\": false, \"data\" : null,"
						+ "\"error\": {\"code\": \"ROOM-01\", \"message\": \"존재하지 않는 그룹입니다.\"}}")))
		}
	)
	ApiResult<FindRoomResponseDto> findRoom(final Long roomId);

	@Operation(summary = "초대 코드를 통한 목표 방 조회")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "방 조회 성공"),
			@ApiResponse(responseCode = "400", description = "유효하지 않은 초대코드",
				content = @Content(schema = @Schema(
					example = "{\"success\": false, \"data\" : null,"
						+ "\"error\": {\"code\": \"ROOM-02\", \"message\": \"유효하지 않은 초대코드입니다.\"}}")))
		}
	)
	ApiResult<FindRoomByCodeResponseDto> findRoomByInviteCode(final String inviteCode);

	@Operation(summary = "목표 방 입장")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "방 입장 성공"),
			@ApiResponse(responseCode = "400", description = "유효하지 않은 초대코드",
				content = @Content(schema = @Schema(
					example = "{\"success\": false, \"data\" : null,"
				+ "\"error\": {\"code\": \"ROOM-02\", \"message\": \"유효하지 않은 초대코드입니다.\"}}"))),
			@ApiResponse(responseCode = "420", description = "정원 초과",
				content = @Content(schema = @Schema(
					example = "{\"success\": false, \"data\" : null,"
						+ "\"error\": {\"code\": \"ROOM-03\", \"message\": \"정원을 초과하였습니다\"}}")))

		}
	)
	ApiResult<RoomIdResponseDto> enterRoom(final EnterRoomRequestDto enterRoomRequestDto);
}