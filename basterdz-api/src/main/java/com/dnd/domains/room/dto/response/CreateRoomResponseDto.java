package com.dnd.domains.room.dto.response;

import com.dnd.room.Room;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class CreateRoomResponseDto {

    @Schema(name = "id", example = "1")
    private Long id;

    public static CreateRoomResponseDto from(final Room room) {
        return CreateRoomResponseDto.builder()
                .id(room.getId())
                .build();
    }
}
