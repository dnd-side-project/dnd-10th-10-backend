package com.dnd.api.domains.room.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.dnd.api.common.random.LocalDateHolder;
import com.dnd.domain.ranking.RankingReport;
import com.dnd.domain.room.dto.RoomMemberDailyScreenTime;
import com.dnd.domain.room.implement.RoomReportFinder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomReportService {

	private final RoomReportFinder roomReportFinder;
	private final LocalDateHolder localDateHolder;


	public List<RoomMemberDailyScreenTime> findRoomMemberScreenTime(Long roomId) {
		LocalDate today = localDateHolder.today();
		return roomReportFinder.findRoomMemberDailyScreenTime(roomId, today);
	}

	public List<RankingReport> findRankingReport(Long roomId, LocalDate usageDate) {
		return roomReportFinder.findRankingReport(roomId, usageDate);
	}

}
