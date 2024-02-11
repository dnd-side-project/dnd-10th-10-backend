package com.dnd.api.domains.room.controller;

import com.dnd.api.domains.room.dto.FindRoomResponse;
import com.dnd.api.domains.room.dto.FindRoomByCodeResponse;

import com.dnd.api.common.dto.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "\uD83C\uDFE0Room", description = "Room API")
public interface RoomApiPresentation {

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
	ApiResult<FindRoomResponse> findRoom(final Long roomId);

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
	ApiResult<FindRoomByCodeResponse> findRoomByInviteCode(final String inviteCode);
}
