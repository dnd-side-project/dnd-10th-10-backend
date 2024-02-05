package com.dnd.domains.room.dto.response;

import com.dnd.room.Room;
import com.dnd.room.vo.RestrictApp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
public class FindRoomResponseDto {

    @Schema(name = "id", example = "1")
    private Long id;

    @Schema(name = "title", example = "우리들의 도파민 탈출기")
    private String title;

    @Schema(name = "goal", example = "우리 시험기간에만 인스타 하지 말아보자")
    private String goal;

    @Schema(name = "restrictApp", example = "인스타그램")
    private RestrictApp restrictApp;

    @Schema(name = "startDate", example = "2024-01-23")
    private LocalDate startDate;

    @Schema(name = "endDate", example = "2024-01-25")
    private LocalDate endDate;

    @Schema(name = "limitHour", example = "2")
    private int limitHour;

    @Schema(name = "personnel", example = "6")
    private int personnel;

    @Schema(name = "memberCount", example = "4")
    private int memberCount;

    @Schema(name = "remainingDay", example = "10")
    private int remainingDay;

    @Schema(name = "inviteCode", example = "inviteMe")
    private String inviteCode;

    public static FindRoomResponseDto from(Room room) {
        return FindRoomResponseDto.builder()
                .id(room.getId())
                .title(room.getTitle())
                .goal(room.getGoal())
                .restrictApp(room.getRestrictApp())
                .startDate(room.getStartDate())
                .endDate(room.getEndDate())
                .limitHour(room.getLimitHour())
                .personnel(room.getPersonnel())
                .memberCount(room.getMemberCount())
                .remainingDay(room.getRemainingDay())
                .inviteCode(room.getInviteCode())
                .build();
    }
}
