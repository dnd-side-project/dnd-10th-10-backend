package com.dnd.api.domains.room.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.room.dto.RoomMemberRankingResponse;
import com.dnd.api.domains.room.dto.RoomMemberScreenTimeResponse;
import com.dnd.api.domains.room.service.RoomReportService;
import com.dnd.domain.ranking.RankingReport;
import com.dnd.domain.room.dto.RoomMemberDailyScreenTime;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomReportController implements RoomReportApiPresentation {

	private final RoomReportService roomReportService;

	@GetMapping("/{roomId}/screen-time")
	public ApiResult<List<RoomMemberScreenTimeResponse>> findRoomMemberScreenTime(@PathVariable Long roomId) {
		List<RoomMemberDailyScreenTime> roomMemberDailyScreenTimes = roomReportService.findRoomMemberScreenTime(roomId);
		return ApiResult.ok(roomMemberDailyScreenTimes.stream().map(RoomMemberScreenTimeResponse::from).toList());
	}

	@GetMapping("/{roomId}/rank")
	public ApiResult<List<RoomMemberRankingResponse>> findRoomMemberRanking(
		@PathVariable Long roomId, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		List<RankingReport> rankingReport = roomReportService.findRankingReport(roomId, date);
		return ApiResult.ok(rankingReport.stream().map(RoomMemberRankingResponse::from).toList());
	}
}
