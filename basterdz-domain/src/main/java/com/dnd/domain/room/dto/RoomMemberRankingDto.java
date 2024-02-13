package com.dnd.domain.room.dto;

import java.time.LocalDate;

import com.dnd.domain.vo.RestrictApp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RoomMemberRankingDto {

	private final Long roomId;

	private final Long memberId;

	private final RestrictApp restrictApp;

	private final int rank;

	private final int duration;

	private final LocalDate usageDate;

}
