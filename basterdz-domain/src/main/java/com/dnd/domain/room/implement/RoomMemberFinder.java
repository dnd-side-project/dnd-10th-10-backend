package com.dnd.domain.room.implement;

import com.dnd.common.exception.BadRequestException;
import com.dnd.domain.common.annotation.Finder;
import com.dnd.domain.member.entity.Member;
import com.dnd.domain.room.entity.RoomMember;
import com.dnd.domain.room.repository.RoomMemberJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dnd.common.exception.ErrorCode.MEMBER_ALREADY_ENTERED;

@Finder
@RequiredArgsConstructor
public class RoomMemberFinder {

    private final RoomMemberJpaRepository roomMemberJpaRepository;

    public List<RoomMember> findRoom(final Long roomId) {
        return roomMemberJpaRepository.findByRoomId(roomId);
    }

    public void existsMember(final Member member) {
        if (roomMemberJpaRepository.existsByMemberId(member.getId())) {
            throw new BadRequestException(MEMBER_ALREADY_ENTERED);
        }
    }
}
