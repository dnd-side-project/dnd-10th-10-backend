package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
public class FindActiveRoomsResponse {

    @Schema(name = "roomResponses", example = "[{\"id\":1,\"title\":\"우리들의 도파민 탈출기\",\"personnel\":6,\"memberCount\":4,\"remainingDay\":10}]")
    private List<RoomResponse> roomResponses;

    public static FindActiveRoomsResponse from() {
        return FindActiveRoomsResponse.builder()
                .roomResponses(null)
                .build();
    }
}
