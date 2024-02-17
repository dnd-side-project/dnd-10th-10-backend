package com.dnd.domain.room.implement;

import java.time.LocalDate;
import java.util.List;

import com.dnd.domain.common.annotation.Finder;
import com.dnd.domain.room.dto.RoomMemberDailyScreenTime;
import com.dnd.domain.room.dto.RoomMemberRankingDto;
import com.dnd.domain.room.repository.RoomMemberQueryRepository;

import lombok.RequiredArgsConstructor;

@Finder
@RequiredArgsConstructor
public class RoomReportFinder {

	private final RoomFinder roomFinder;
	private final RoomMemberQueryRepository roomMemberQueryRepository;

	public List<RoomMemberRankingDto> findRoomMemberRankingDto(final LocalDate usageDate) {
		return roomMemberQueryRepository.searchRoomMemberRankingByUsageDate(usageDate);
	}

	public List<RoomMemberDailyScreenTime> findRoomMemberDailyScreenTime(Long roomId, LocalDate today) {
		roomFinder.find(roomId);
		return roomMemberQueryRepository.findRoomMemberScreenTimeByRoomIdAndLocalDate(roomId, today);
	}
}
