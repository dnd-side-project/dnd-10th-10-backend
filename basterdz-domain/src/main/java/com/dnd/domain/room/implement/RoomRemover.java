package com.dnd.domain.room.implement;

import com.dnd.domain.common.annotation.Remover;
import com.dnd.domain.room.repository.RoomJpaRepository;

import lombok.RequiredArgsConstructor;

@Remover
@RequiredArgsConstructor
public class RoomRemover {

	private final RoomJpaRepository roomJpaRepository;

	public void deleteAll() {
		roomJpaRepository.deleteAllInBatch();
	}
}
