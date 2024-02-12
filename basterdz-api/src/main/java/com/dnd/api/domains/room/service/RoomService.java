package com.dnd.api.domains.room.service;

import static com.dnd.common.exception.ErrorCode.ALREADY_STARED_ROOM;
import static com.dnd.common.exception.ErrorCode.NOT_EXIST_HOST;
import static com.dnd.domain.vo.RoomStatus.ACTIVE;

import com.dnd.api.domains.room.util.InviteCodeUtil;
import com.dnd.api.domains.room.dto.CreateRoomRequest;
import com.dnd.common.exception.BadRequestException;
import com.dnd.domain.member.entity.Member;
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
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    public static final boolean IS_HOST = true;
    public static final boolean IS_NOT_HOST = false;
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

        RoomMember roomMember = RoomMember.of(member, room, IS_HOST);
        roomMemberAppender.append(roomMember);

        return roomAppender.append(room);
    }

    @Transactional
    public Room enterRoom(final String inviteCode, final Member member) {
        roomMemberFinder.existsMember(member);

        Room room = roomFinder.findByInviteCode(inviteCode);
        RoomMember roomMember = RoomMember.of(member, room, IS_NOT_HOST);

        room.addMemberCount();

        roomMemberAppender.append(roomMember);
        return roomAppender.append(room);
    }

    @Transactional
    public Room startRoom(final Member member, final Long roomId) {
        findRoom(roomId);

        List<RoomMember> roomMemberList = roomMemberFinder.findRoomMember(roomId);
        RoomMember roomMember = findHostRoomMember(roomMemberList);
        roomMember.isEqualsMember(member.getId());

        Room room = roomMember.getRoom();
        if (room.getStatus() == ACTIVE) {
            throw new BadRequestException(ALREADY_STARED_ROOM);
        }
        room.changeStatus(ACTIVE);
        return roomAppender.append(room);
    }

    public Room findRoom(final Long roomId) {
        return roomFinder.find(roomId);
    }

    public Room findRoomByInviteCode(final String inviteCode) {
        return roomFinder.findByInviteCode(inviteCode);
    }

    private RoomMember findHostRoomMember(final List<RoomMember> roomMemberList) {
        return roomMemberList.stream()
                .filter(roomMember -> roomMember.isHost() == IS_HOST)
                .findFirst()
                .orElseThrow(() -> new BadRequestException(NOT_EXIST_HOST));
    }
}
