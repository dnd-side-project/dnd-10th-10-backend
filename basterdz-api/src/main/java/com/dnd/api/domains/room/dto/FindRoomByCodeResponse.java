package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import com.dnd.domain.room.entity.Room;
import com.dnd.domain.vo.RestrictApp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
public class FindRoomByCodeResponse {

    @Schema(name = "id", example = "1")
    private Long id;

    @Schema(name = "title", example = "우리들의 도파민 탈출기")
    private String title;

    @Schema(name = "restrictApp", example = "INSTAGRAM")
    private RestrictApp restrictApp;

    @Schema(name = "limitHour", example = "2")
    private int limitHour;

    @Schema(name = "targetDay", example = "26")
    private int targetDay;

    public static FindRoomByCodeResponse from(final Room room) {
        return FindRoomByCodeResponse.builder()
                .id(room.getId())
                .title(room.getTitle())
                .restrictApp(room.getRestrictApp())
                .limitHour(room.getLimitHour())
                .targetDay(room.getTargetDay())
                .build();
    }
}
