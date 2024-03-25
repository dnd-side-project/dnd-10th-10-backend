package com.dnd.domain.room.implement;

import com.dnd.common.exception.BadRequestException;
import com.dnd.common.exception.NotFoundException;
import com.dnd.domain.common.annotation.Finder;
import com.dnd.domain.member.entity.Member;
import com.dnd.domain.room.dto.ActiveRoom;
import com.dnd.domain.room.entity.Room;
import com.dnd.domain.room.entity.RoomMember;
import com.dnd.domain.room.repository.RoomMemberJpaRepository;
import com.dnd.domain.room.repository.RoomMemberQueryRepository;
import com.dnd.domain.vo.RestrictApp;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dnd.common.exception.ErrorCode.*;

@Finder
@RequiredArgsConstructor
public class RoomMemberFinder {

    private final RoomMemberJpaRepository roomMemberJpaRepository;
    private final RoomMemberQueryRepository roomMemberQueryRepository;

    public RoomMember findRoomMember(final Member member, final Room room) {
        return roomMemberJpaRepository.findRoomMemberByMemberAndRoom(member, room)
                .orElseThrow(() -> new NotFoundException(ROOM_MEMBER_NOT_FOUND));
    }

    public void checkExistsMember(final Member member, final Room room) {
        if (roomMemberJpaRepository.existsByMemberAndRoom(member, room)) {
            throw new BadRequestException(MEMBER_ALREADY_ENTERED);
        }
    }

    public List<RoomMember> findRoomMembers(final Room room) {
        return roomMemberJpaRepository.findRoomMembers(room);
    }

    public List<ActiveRoom> findActiveRooms(final Member member, final RestrictApp restrictApp) {
        return roomMemberQueryRepository.findActiveRoomsByMember(member.getId(), restrictApp);
    }
}
