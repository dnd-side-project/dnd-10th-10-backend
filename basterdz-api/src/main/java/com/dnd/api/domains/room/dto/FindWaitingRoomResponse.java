package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import com.dnd.domain.member.entity.Member;
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
    private List<Member> members;

    public static FindWaitingRoomResponse from() {
        return FindWaitingRoomResponse.builder()
                .memberCount(0)
                .personnel(0)
                .members(null)
                .build();
    }
}
