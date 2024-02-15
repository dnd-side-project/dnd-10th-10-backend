package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = PROTECTED)
public class RoomResponse {

    private Long id;

    private String title;

    private int personnel;

    private int memberCount;

    private int remainingDay;
}
