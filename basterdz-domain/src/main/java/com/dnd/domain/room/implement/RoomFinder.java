package com.dnd.domain.room.implement;

import static com.dnd.common.exception.ErrorCode.INVALID_INVITE_CODE;
import static com.dnd.common.exception.ErrorCode.ROOM_NOT_FOUND;

import com.dnd.common.exception.BadRequestException;
import com.dnd.common.exception.NotFoundException;
import com.dnd.domain.common.annotation.Finder;
import com.dnd.domain.room.repository.RoomJpaRepository;
import com.dnd.domain.room.entity.Room;

import lombok.RequiredArgsConstructor;

@Finder
@RequiredArgsConstructor
public class RoomFinder {

	private final RoomJpaRepository roomJpaRepository;

	public Room find(final Long roomId) {
		return roomJpaRepository.findById(roomId)
			.orElseThrow(() -> new NotFoundException(ROOM_NOT_FOUND));
	}

	public Room findByInviteCode(final String inviteCode) {
		return roomJpaRepository.findByInviteCode(inviteCode)
			.orElseThrow(() -> new BadRequestException(INVALID_INVITE_CODE));
	}
}
