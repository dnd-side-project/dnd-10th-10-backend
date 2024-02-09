package com.dnd.domain.room.implement;

import com.dnd.domain.common.annotation.Appender;
import com.dnd.domain.room.RoomJpaRepository;
import com.dnd.domain.room.entity.Room;

import lombok.RequiredArgsConstructor;

@Appender
@RequiredArgsConstructor
public class RoomAppender {

	private final RoomJpaRepository roomJpaRepository;

	public Room append(Room room) {
		return roomJpaRepository.save(room);
	}
}
