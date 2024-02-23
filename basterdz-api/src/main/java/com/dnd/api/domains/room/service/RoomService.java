package com.dnd.api.domains.room.service;

import static com.dnd.common.exception.ErrorCode.NOT_ROOM_HOST;
import static com.dnd.domain.room.entity.RoomStatus.ACTIVE;

import com.dnd.api.domains.room.dto.FindWaitingRoomResponse;
import com.dnd.api.domains.room.dto.RoomMemberResponse;
import com.dnd.api.domains.room.util.InviteCodeUtil;
import com.dnd.api.domains.room.dto.CreateRoomRequest;
import com.dnd.common.exception.BadRequestException;
import com.dnd.domain.member.entity.Member;
import com.dnd.domain.room.entity.Room;
import com.dnd.domain.room.entity.RoomMember;
import com.dnd.domain.room.implement.*;

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
    private final RoomRemover roomRemover;
    private final RoomMemberFinder roomMemberFinder;
    private final RoomMemberAppender roomMemberAppender;
    private final InviteCodeUtil inviteCodeUtil;

    @Transactional
    public Room createRoom(
            final CreateRoomRequest requestDto,
            final Member member
    ) {
        String inviteCode = inviteCodeUtil.generate().toUpperCase();
        Room room = requestDto.toEntity(inviteCode);

        RoomMember roomMember = RoomMember.of(member, room, IS_HOST);
        roomMemberAppender.append(roomMember);

        return roomAppender.append(room);
    }

    @Transactional
    public Room enterRoom(final String inviteCode, final Member member) {
        Room room = findRoomByInviteCode(inviteCode);
        roomMemberFinder.checkExistsMember(member, room);

        RoomMember roomMember = RoomMember.of(member, room, IS_NOT_HOST);

        room.addMemberCount();

        roomMemberAppender.append(roomMember);
        return room;
    }

    @Transactional
    public Room startRoom(final Member member, final Long roomId) {
        Room room = findRoom(roomId);
        RoomMember roomMember = roomMemberFinder.findRoomMember(member, room);
        if (roomMember.isHost()) {
            room.changeStatus(ACTIVE);
        }
        return room;
    }

    public FindWaitingRoomResponse findWaitingRoom(final Long roomId) {
        Room room = findRoom(roomId);
        List<RoomMember> roomMembers = roomMemberFinder.findRoomMembers(room);
        List<RoomMemberResponse> roomMemberResponses = RoomMemberResponse.from(roomMembers);
        return FindWaitingRoomResponse.createFindWaitingRoomResponse(room, roomMemberResponses);
    }

    @Transactional
    public Room deleteRoom(final Member member, final Long roomId) {
        if (!checkHost(member, roomId)) {
            throw new BadRequestException(NOT_ROOM_HOST);
        }

        Room room = findRoom(roomId);
        roomRemover.deleteRoom(room);
        return room;
    }

    public boolean checkHost(final Member member, final Long roomId) {
        Room room = findRoom(roomId);
        RoomMember roomMember = roomMemberFinder.findRoomMember(member, room);
        return roomMember.isHost();
    }

    public Room findRoom(final Long roomId) {
        return roomFinder.findRoom(roomId);
    }

    public Room findRoomByInviteCode(final String inviteCode) {
        return roomFinder.findRoomByInviteCode(inviteCode);
    }

}
