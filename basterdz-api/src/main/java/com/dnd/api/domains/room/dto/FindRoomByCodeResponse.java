package com.dnd.api.domains.room.dto;

import com.dnd.domain.room.entity.Room;
import com.dnd.domain.vo.RestrictApp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
public class FindRoomByCodeResponse {

    @Schema(name = "id", example = "1")
    private Long id;

    @Schema(name = "restrictApp", example = "INSTAGRAM")
    private RestrictApp restrictApp;

    @Schema(name = "startDate", example = "2024-01-23")
    private LocalDate startDate;

    @Schema(name = "endDate", example = "2024-01-25")
    private LocalDate endDate;

    @Schema(name = "limitHour", example = "2")
    private int limitHour;

    public static FindRoomByCodeResponse from(final Room room) {
        return FindRoomByCodeResponse.builder()
                .id(room.getId())
                .restrictApp(room.getRestrictApp())
                .startDate(room.getStartDate())
                .endDate(room.getEndDate())
                .limitHour(room.getLimitHour())
                .build();
    }
}
