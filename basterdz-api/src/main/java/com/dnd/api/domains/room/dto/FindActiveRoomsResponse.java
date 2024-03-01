package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import com.dnd.domain.room.dto.ActiveRoom;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
public class FindActiveRoomsResponse {

    @Schema(name = "roomCount", example = "6")
    private int roomCount;

    @Schema(name = "roomResponses", example = "[{\"id\":1,\"title\":\"우리들의 도파민 탈출기\",\"personnel\":6,\"memberCount\":4,\"remainingDay\":10}]")
    private List<ActiveRoom> activeRooms;

    public static FindActiveRoomsResponse from(final List<ActiveRoom> activeRooms, final int roomCount) {
        return FindActiveRoomsResponse.builder()
                .roomCount(roomCount)
                .activeRooms(activeRooms)
                .build();
    }
}
