package com.dnd.domain.room.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomStatus {
	WAITING, ACTIVE, FINISHED
}
