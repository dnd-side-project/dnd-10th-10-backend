package com.dnd.domains.room.dto.request;

import com.dnd.room.Room;
import com.dnd.room.vo.RestrictApp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateRoomRequestDto {

    public static final int MINIMUM_MEMBER_COUNT = 1;

    @Schema(description = "방 제목", example = "우리들의 도파민 탈출기")
    @NotBlank(message = "방 제목을 입력해 주세요.")
    @Size(max = 20, message = "방 제목은 20자를 초과할 수 없습니다.")
    private String title;

    @Schema(description = "목표 한마디", example = "갓생살자!갓생살자!")
    @NotBlank(message = "목표를 입력해 주세요.")
    @Size(max = 30, message = "목표는 30자를 초과할 수 없습니다.")
    private String goal;

    @Schema(description = "참여 정원", example = "6")
    @NotNull(message = "인원 수를 입력해 주세요.")
    @Max(value = 6, message = "인원 수는 6명을 초과할 수 없습니다.")
    @Min(value = 1, message = "인원 수는 1명 미만일 수 없습니다.")
    private int personnel;

    @Schema(description = "제한 앱 설정", example = "INSTAGRAM")
    @NotNull(message = "제한 앱을 설정해 주세요.")
    private RestrictApp restrictApp;

    @Schema(description = "시작 날짜", example = "2024-01-23")
    @NotNull(message = "기한을 설정해 주세요.")
    private LocalDate startDate;

    @Schema(description = "종료 날짜", example = "2024-01-25")
    @NotNull(message = "기한을 설정해 주세요.")
    private LocalDate endDate;

    @Schema(description = "하루 총 제한 시간", example = "1시간")
    @NotNull(message = "하루 총 제한 시간을 설정해 주세요.")
    @Max(value = 6, message = "제한 시간은 6시간을 초과할 수 없습니다.")
    @Min(value = 1, message = "제한 시간은 1시간 미만일 수 없습니다.")
    private int limitHour;

    public Room toEntity(final String code, final int remainingDay) {
        return Room.builder()
                .title(title)
                .goal(goal)
                .personnel(personnel)
                .restrictApp(restrictApp)
                .inviteCode(code)
                .startDate(startDate)
                .endDate(endDate)
                .limitHour(limitHour)
                .remainingDay(remainingDay)
                .memberCount(MINIMUM_MEMBER_COUNT)
                .build();
    }

    @Builder
    public CreateRoomRequestDto(
            final String title, final String goal,
            final int personnel, final RestrictApp restrictApp,
            final LocalDate startDate, final LocalDate endDate,
            final int limitHour
    ) {
        this.title = title;
        this.goal = goal;
        this.personnel = personnel;
        this.restrictApp = restrictApp;
        this.startDate = startDate;
        this.endDate = endDate;
        this.limitHour = limitHour;
    }
}
