package com.dnd.domain.room.implement;

import static com.dnd.common.exception.ErrorCode.MEMBER_ALREADY_ENTERED;
import static com.dnd.common.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.dnd.common.exception.BadRequestException;
import com.dnd.domain.common.annotation.Finder;
import com.dnd.domain.member.entity.Member;
import com.dnd.domain.room.entity.Room;
import com.dnd.domain.room.entity.RoomMember;
import com.dnd.domain.room.repository.RoomMemberJpaRepository;
import lombok.RequiredArgsConstructor;

@Finder
@RequiredArgsConstructor
public class RoomMemberFinder {

    private final RoomMemberJpaRepository roomMemberJpaRepository;

    public RoomMember findRoomMember(final Member member, final Room room) {
        return roomMemberJpaRepository.findRoomMemberByMemberAndRoom(member, room)
                .orElseThrow(() -> new BadRequestException(MEMBER_NOT_FOUND));
    }

    public void existsMember(final Member member) {
        if (roomMemberJpaRepository.existsByMemberId(member.getId())) {
            throw new BadRequestException(MEMBER_ALREADY_ENTERED);
        }
    }
}
