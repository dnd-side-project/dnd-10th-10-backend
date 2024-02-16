package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import com.dnd.domain.room.entity.RoomMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
public class RoomMemberResponse {

    private Long id;

    private String nickname;

    public static List<RoomMemberResponse> from(final List<RoomMember> roomMembers) {
        return roomMembers.stream()
                .map(roomMember -> RoomMemberResponse.builder()
                        .id(roomMember.getMember().getId())
                        .nickname(roomMember.getMember().getNickname())
                        .build())
                .collect(Collectors.toList());
    }
}
