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
public class FindWaitingRoomResponse {

    @Schema(name = "memberCount", example = "4")
    private int memberCount;

    @Schema(name = "personnel", example = "6")
    private int personnel;

    @Schema(name = "members", example = "[{\"id\":1,\"nickname\":\"바밤바\"},{\"id\":2,\"nickname\":\"비비박\"}]")
    private List<RoomMemberResponse> members;

    public static FindWaitingRoomResponse from(
            final Room room,
            final List<RoomMemberResponse> roomMemberResponses
    ) {

        return FindWaitingRoomResponse.builder()
                .memberCount(room.getMemberCount())
                .personnel(room.getPersonnel())
                .members(roomMemberResponses)
                .build();
    }
}
