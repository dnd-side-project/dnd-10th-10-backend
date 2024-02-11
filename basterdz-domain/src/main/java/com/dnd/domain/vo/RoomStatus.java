package com.dnd.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomStatus {

    WAITING("waiting"),
    ACTIVE("active"),
    FINISHED("finished");

    private final String status;

}
