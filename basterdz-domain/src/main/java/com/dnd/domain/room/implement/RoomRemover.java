package com.dnd.domain.room.implement;

import com.dnd.domain.common.annotation.Remover;
import com.dnd.domain.room.entity.Room;
import com.dnd.domain.room.repository.RoomJpaRepository;

import lombok.RequiredArgsConstructor;

@Remover
@RequiredArgsConstructor
public class RoomRemover {

	private final RoomJpaRepository roomJpaRepository;

	public void deleteRoom(final Room room) {
		roomJpaRepository.delete(room);
	}

	public void deleteAll() {
		roomJpaRepository.deleteAllInBatch();
	}
}
