package com.dnd.api.domains.room.dto;

import com.dnd.common.util.TimeConvertUtils;
import com.dnd.domain.ranking.RankingReport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomMemberRankingResponse {

	@Schema(name = "ranking", example = "1")
	private int ranking;

	@Schema(name = "nickname", example = "최은주")
	private String nickname;

	@Schema(name = "usageTime", example = "01:20")
	private String usageTime;

	public static RoomMemberRankingResponse from(RankingReport rankingReport) {
		return RoomMemberRankingResponse.builder()
			.ranking(rankingReport.getRanking())
			.nickname(rankingReport.getNickname())
			.usageTime(TimeConvertUtils.convertMinutesToTimeString(rankingReport.getDuration()))
			.build();
	}
}
