package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import com.dnd.domain.room.entity.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
public class FindFinishedRoomsResponse {

    @Schema(name = "rooms", example = "[{\"id\":1,\"title\":\"우리들의 도파민 탈출기\",\"personnel\":6,\"memberCount\":4}]")
    private List<Room> rooms;

    public static FindFinishedRoomsResponse from() {
        return FindFinishedRoomsResponse.builder()
                .rooms(null)
                .build();
    }
}
