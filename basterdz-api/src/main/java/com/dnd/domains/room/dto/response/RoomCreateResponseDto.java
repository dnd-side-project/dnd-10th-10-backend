package com.dnd.domains.room.dto.response;

import com.dnd.room.Room;
import com.dnd.room.vo.RestrictApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class RoomCreateResponseDto {

    private Long id;
    private String title;
    private RestrictApp restrictApp;
    private int limitHour;

    public static RoomCreateResponseDto from(final Room room) {
        return RoomCreateResponseDto.builder()
                .id(room.getId())
                .title(room.getTitle())
                .restrictApp(room.getRestrictApp())
                .limitHour(room.getLimitHour())
                .build();
    }
}