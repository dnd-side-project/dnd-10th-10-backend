package com.dnd.api.domains.room.dto;

import com.dnd.common.util.TimeConvertUtils;
import com.dnd.domain.ranking.RankingReport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomMemberRankingResponse {

	private int ranking;

	private String nickname;

	private String usageTime;

	public static RoomMemberRankingResponse from(RankingReport rankingReport) {
		return RoomMemberRankingResponse.builder()
			.ranking(rankingReport.getRanking())
			.nickname(rankingReport.getNickname())
			.usageTime(TimeConvertUtils.convertMinutesToTimeString(rankingReport.getDuration()))
			.build();
	}
}
