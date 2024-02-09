package com.dnd.api.domains.room.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EnterRoomRequestDto {
    @Schema(description = "초대 코드", example = "YXKRN8QS")
    @NotBlank(message = "초대 코드를 입력해 주세요")
    @Size(max = 8, message = "초대 코드는 8자를 초과할 수 없습니다.")
    private String inviteCode;
}
