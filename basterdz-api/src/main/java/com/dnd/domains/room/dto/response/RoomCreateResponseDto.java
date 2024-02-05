package com.dnd.domains.room.dto.response;

import com.dnd.room.Room;
import com.dnd.room.vo.RestrictApp;

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
public class RoomCreateResponseDto {

    @Schema(name = "id", example = "1")
    private Long id;

    public static RoomCreateResponseDto from(final Room room) {
        return RoomCreateResponseDto.builder()
                .id(room.getId())
                .build();
    }
}
