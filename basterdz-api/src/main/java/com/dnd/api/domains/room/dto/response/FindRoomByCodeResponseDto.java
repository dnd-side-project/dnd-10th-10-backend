package com.dnd.api.domains.room.dto.response;

import com.dnd.domain.room.Room;
import com.dnd.domain.room.vo.RestrictApp;
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
public class FindRoomByCodeResponseDto {

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

    public static FindRoomByCodeResponseDto from(final Room room) {
        return FindRoomByCodeResponseDto.builder()
                .id(room.getId())
                .restrictApp(room.getRestrictApp())
                .startDate(room.getStartDate())
                .endDate(room.getEndDate())
                .limitHour(room.getLimitHour())
                .build();
    }
}
