package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import com.dnd.domain.room.dto.RoomMemberDailyScreenTime;
import com.dnd.common.util.TimeConvertUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class RoomMemberScreenTimeResponse {

	@Schema(name = "memberId", example = "1")
	private Long memberId;

	@Schema(name = "nickName", example = "지환")
	private String nickName;

	@Schema(name = "nickName", example = "02:00")
	private String usageTime;

	@Schema(name = "percent", example = "0.5")
	private double percent;

	@Schema(name = "percent", example = "false")
	private boolean isExceed;

	public static RoomMemberScreenTimeResponse from(RoomMemberDailyScreenTime roomMemberDailyScreenTime) {
		return RoomMemberScreenTimeResponse.builder()
			.memberId(roomMemberDailyScreenTime.getMemberId())
			.nickName(roomMemberDailyScreenTime.getNickName())
			.usageTime(TimeConvertUtils.convertMinutesToTimeString(roomMemberDailyScreenTime.getDuration()))
			.percent(roomMemberDailyScreenTime.getPercent())
			.isExceed(roomMemberDailyScreenTime.getIsExceed())
			.build();
	}


}
