package com.dnd.domain.room.implement;

import static com.dnd.common.exception.ErrorCode.INVALID_INVITE_CODE;
import static com.dnd.common.exception.ErrorCode.ROOM_NOT_FOUND;

import java.time.LocalDate;
import java.util.List;

import com.dnd.common.exception.BadRequestException;
import com.dnd.common.exception.NotFoundException;
import com.dnd.domain.common.annotation.Finder;
import com.dnd.domain.room.repository.RoomJpaRepository;
import com.dnd.domain.room.repository.RoomMemberQueryRepository;
import com.dnd.domain.room.dto.RoomMemberRankingDto;
import com.dnd.domain.room.entity.Room;

import lombok.RequiredArgsConstructor;

@Finder
@RequiredArgsConstructor
public class RoomFinder {

	private final RoomJpaRepository roomJpaRepository;
	private final RoomMemberQueryRepository roomMemberQueryRepository;

	public Room findRoom(final Long roomId) {
		return roomJpaRepository.findById(roomId)
			.orElseThrow(() -> new NotFoundException(ROOM_NOT_FOUND));
	}

	public Room findRoomByInviteCode(final String inviteCode) {
		return roomJpaRepository.findByInviteCode(inviteCode)
			.orElseThrow(() -> new BadRequestException(INVALID_INVITE_CODE));
	}

	public List<RoomMemberRankingDto> findRoomMemberRankingDto(final LocalDate usageDate) {
		return roomMemberQueryRepository.searchRoomMemberRankingByUsageDate(usageDate);
	}

}
