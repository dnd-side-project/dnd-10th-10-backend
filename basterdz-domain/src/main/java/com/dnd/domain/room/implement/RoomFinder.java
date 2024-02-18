package com.dnd.domain.room.implement;

import static com.dnd.common.exception.ErrorCode.*;
import static com.dnd.domain.room.entity.RoomStatus.WAITING;

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

	public Room findRoom(final Long roomId) {
		return roomJpaRepository.findById(roomId)
			.orElseThrow(() -> new NotFoundException(ROOM_NOT_FOUND));
	}

	public Room findRoomByInviteCode(final String inviteCode) {
		Room room = roomJpaRepository.findByInviteCode(inviteCode)
				.orElseThrow(() -> new BadRequestException(INVALID_INVITE_CODE));

		if (!room.getStatus().equals(WAITING)) {
			throw new BadRequestException(ALREADY_STARTED_ROOM);
		}

		return room;
	}
}
