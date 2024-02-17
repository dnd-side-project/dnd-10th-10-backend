package com.dnd.domain.room.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomMemberDailyScreenTime {

	private Long memberId;

	private String nickName;

	private int limitHour;

	private int duration;

	public double getPercent() {
		double ratio = (double)duration / (limitHour*60);
		if(ratio > 1.0) ratio = 1.0;
		return Math.round(ratio * 100)/100.0;
	}

	public boolean getIsExceed() {
		return duration > limitHour;
	}
}
