package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import com.dnd.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
public class RoomMemberResponse {

    private Long id;

    private String nickname;

    public static RoomMemberResponse from(final Member member) {
        return RoomMemberResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .build();
    }
}
