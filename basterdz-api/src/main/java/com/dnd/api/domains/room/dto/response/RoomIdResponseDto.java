package com.dnd.api.domains.room.dto.response;

import com.dnd.domain.room.entity.Room;

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
public class RoomIdResponseDto {

    @Schema(name = "id", example = "1")
    private Long id;

    public static RoomIdResponseDto from(final Room room) {
        return RoomIdResponseDto.builder()
                .id(room.getId())
                .build();
    }
}
