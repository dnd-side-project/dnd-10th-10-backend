package com.dnd.api.domains.room.service;

import com.dnd.api.domains.room.util.InviteCodeUtil;
import com.dnd.api.domains.room.dto.CreateRoomRequest;
import com.dnd.domain.member.Member;
import com.dnd.domain.room.entity.Room;
import com.dnd.domain.room.entity.RoomMember;
import com.dnd.domain.room.implement.RoomAppender;
import com.dnd.domain.room.implement.RoomFinder;

import com.dnd.domain.room.implement.RoomMemberAppender;
import com.dnd.domain.room.implement.RoomMemberFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final RoomFinder roomFinder;
    private final RoomAppender roomAppender;
    private final RoomMemberFinder roomMemberFinder;
    private final RoomMemberAppender roomMemberAppender;
    private final InviteCodeUtil inviteCodeUtil;

    @Transactional
    public Room createRoom(
            final CreateRoomRequest requestDto,
            final LocalDate registerDate, final Member member
    ) {
        String inviteCode = inviteCodeUtil.generate().toUpperCase();
        Period period = Period.between(registerDate, requestDto.getEndDate());
        Room room = requestDto.toEntity(inviteCode, period.getDays());

        RoomMember roomMember = RoomMember.of(member, room, true);
        roomMemberAppender.append(roomMember);

        return roomAppender.append(room);
    }

    @Transactional
    public Room enterRoom(final String inviteCode, final Member member) {
        roomMemberFinder.existsMemberByMemberId(member);

        Room room = roomFinder.findByInviteCode(inviteCode);
        RoomMember roomMember = RoomMember.of(member, room, false);

        room.addMemberCount();

        roomMemberAppender.append(roomMember);
        return roomAppender.append(room);
    }

    public Room findRoom(Long roomId) {
        return roomFinder.find(roomId);
    }

    public Room findRoomByInviteCode(String inviteCode) {
        return roomFinder.findByInviteCode(inviteCode);
    }


}
