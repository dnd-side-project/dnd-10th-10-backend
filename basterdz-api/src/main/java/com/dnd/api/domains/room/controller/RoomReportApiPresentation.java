package com.dnd.api.domains.room.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.room.dto.RoomMemberRankingResponse;
import com.dnd.api.domains.room.dto.RoomMemberScreenTimeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "\uD83C\uDFE0Room", description = "Room API")
public interface RoomReportApiPresentation {

	@Operation(summary = "방 내 멤버 현재 스크린 타임 조회")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "스크린 타임 조회 성공"),
			@ApiResponse(responseCode = "404", description = "존재하지 않는 그룹",
				content = @Content(schema = @Schema(
					example = "{\"success\": false, \"data\" : null,"
						+ "\"error\": {\"code\": \"ROOM-01\", \"message\": \"존재하지 않는 그룹입니다.\"}}")))
		}
	)
	ApiResult<List<RoomMemberScreenTimeResponse>> findRoomMemberScreenTime(Long roomId);

	@Operation(summary = "방 내 랭킹 조회")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "랭킹 조회 성공"),
			@ApiResponse(responseCode = "404", description = "존재하지 않는 그룹",
				content = @Content(schema = @Schema(
					example = "{\"success\": false, \"data\" : null,"
						+ "\"error\": {\"code\": \"ROOM-01\", \"message\": \"존재하지 않는 그룹입니다.\"}}")))
		}
	)
	ApiResult<List<RoomMemberRankingResponse>> findRoomMemberRanking(Long roomId, LocalDate date);
}
