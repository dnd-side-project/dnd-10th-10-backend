package com.dnd.domains.room.dto.request;

import com.dnd.room.Room;
import com.dnd.room.vo.RestrictApp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class RoomCreateRequestDto {

    @NotBlank(message = "방 제목을 입력해 주세요.")
    @Size(max = 20, message = "방 제목은 20자를 초과할 수 없습니다.")
    private String title;

    @NotBlank(message = "목표를 입력해 주세요.")
    @Size(max = 30, message = "목표는 30자를 초과할 수 없습니다.")
    private String goalMessage;

    @NotNull(message = "인원 수를 입력해 주세요.")
    @Max(value = 6, message = "인원 수는 6명을 초과할 수 없습니다.")
    @Min(value = 1, message = "인원 수는 1명 미만일 수 없습니다.")
    private int maxHeadcount;

    @NotNull(message = "제한 앱을 설정해 주세요.")
    private RestrictApp restrictApp;

    @NotNull(message = "기한을 설정해 주세요.")
    private LocalDate startDate;

    @NotNull(message = "기한을 설정해 주세요.")
    private LocalDate endDate;

    @NotNull(message = "하루 총 제한 시간을 설정해 주세요.")
    @Max(value = 6, message = "제한 시간은 6시간을 초과할 수 없습니다.")
    @Min(value = 1, message = "제한 시간은 1시간 미만일 수 없습니다.")
    private int limitHour;

    public Room toEntity(final String code) {
        return Room.builder()
                .title(title)
                .goalMessage(goalMessage)
                .maxHeadcount(maxHeadcount)
                .restrictApp(restrictApp)
                .inviteCode(code)
                .startDate(startDate)
                .endDate(endDate)
                .limitHour(limitHour)
                .build();
    }
}