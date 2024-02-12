package com.dnd.api.domains.room.controller;

import com.dnd.api.domains.room.dto.*;

import com.dnd.api.common.dto.ApiResult;

import com.dnd.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

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

	@Operation(summary = "목표 방 생성")
	@ApiResponse(responseCode = "201", description = "방 생성 성공")
	ResponseEntity<ApiResult<RoomIdResponse>> createRoom(final Member member, final CreateRoomRequest roomCreateRequestDto);

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
											+ "\"error\": {\"code\": \"ROOM-03\", \"message\": \"정원을 초과하였습니다.\"}}"))),
					@ApiResponse(responseCode = "420", description = "이미 입장한 회원",
							content = @Content(schema = @Schema(
									example = "{\"success\": false, \"data\" : null,"
											+ "\"error\": {\"code\": \"ROOM-05\", \"message\": \"이미 방에 입장한 회원입니다.\"}}")))
			}
	)
	ApiResult<RoomIdResponse> enterRoom(final Member member, final EnterRoomRequest enterRoomRequestDto);

}
