package com.dnd.batch;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dnd.domain.room.dto.RoomMemberRankingDto;
import com.dnd.domain.room.implement.RoomFinder;
import com.dnd.util.LocalDateHolder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RankingJob {

	private final RoomFinder roomFinder;
	private final LocalDateHolder localDateHolder;

	//TODO 방별 멤버 사용량을 Redis에 저장
	public void run() {
		LocalDate yesterday = localDateHolder.today().minusDays(1);
		List<RoomMemberRankingDto> roomMemberRankings = roomFinder.findRoomMemberRankingDto(yesterday);
	}

}
