package com.dnd.domains.room.controller;

import com.dnd.domains.room.dto.request.EnterRoomRequestDto;
import com.dnd.domains.room.dto.response.FindRoomByCodeResponseDto;
import com.dnd.domains.room.dto.response.FindRoomResponseDto;

import org.springframework.http.ResponseEntity;

import com.dnd.common.dto.ApiResult;
import com.dnd.domains.room.dto.request.CreateRoomRequestDto;
import com.dnd.domains.room.dto.response.CreateRoomResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.examples.Example;

@Tag(name = "Room", description = "Room API")
public interface RoomApiPresentation {

	@Operation(summary = "목표 방 생성")
	@ApiResponse(responseCode = "201", description = "방 생성 성공")
	ResponseEntity<ApiResult<CreateRoomResponseDto>> createRoom(final CreateRoomRequestDto roomCreateRequestDto);

	@Operation(summary = "목표 방 조회")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "방 조회 성공"),
			@ApiResponse(responseCode = "404", description = "해당 그룹을 찾을 수 없습니다.",
				content = @Content(schema = @Schema(
					example = "{\"success\": false, \"data\" : null,"
						+ "\"error\": {\"status\": 404, \"code\": \"ROOM-01\", \"message\": \"존재하지 않는 그룹입니다.\"}}")))
		}
	)
	ApiResult<FindRoomResponseDto> findRoom(final Long roomId);

	@Operation(summary = "초대 코드를 통한 목표 방 조회")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "방 조회 성공"),
			@ApiResponse(responseCode = "404", description = "해당 그룹을 찾을 수 없습니다.",
				content = @Content(schema = @Schema(
					example = "{\"success\": false, \"data\" : null,"
						+ "\"error\": {\"status\": 404, \"code\": \"ROOM-01\", \"message\": \"존재하지 않는 그룹입니다.\"}}")))
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
				+ "\"error\": {\"status\": 400, \"code\": \"ROOM-02\", \"message\": \"유효하지 않은 초대코드입니다.\"}}")))

		}
	)
	ResponseEntity<ApiResult<Void>> enterRoom(final Long roomId, final EnterRoomRequestDto enterRoomRequestDto);
}
