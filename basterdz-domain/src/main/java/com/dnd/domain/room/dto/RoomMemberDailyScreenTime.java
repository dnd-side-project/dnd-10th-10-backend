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
}
