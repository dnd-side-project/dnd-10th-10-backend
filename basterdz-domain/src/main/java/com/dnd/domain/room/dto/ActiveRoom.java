package com.dnd.domain.room.dto;

import com.dnd.domain.vo.RestrictApp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActiveRoom {

    private final Long roomId;
    private final String title;
    private final int personnel;
    private final RestrictApp restrictApp;
    private final int memberCount;
//    private int remainingDay;
}
