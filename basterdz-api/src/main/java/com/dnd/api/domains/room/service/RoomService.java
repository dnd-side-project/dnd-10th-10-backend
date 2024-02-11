package com.dnd.api.domains.room.service;

import com.dnd.api.domains.room.util.InviteCodeUtil;
import com.dnd.api.domains.room.dto.CreateRoomRequest;
import com.dnd.domain.room.entity.Room;
import com.dnd.domain.room.implement.RoomAppender;
import com.dnd.domain.room.implement.RoomFinder;

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
    private final InviteCodeUtil inviteCodeUtil;

    @Transactional
    public Room createRoom(
            final CreateRoomRequest requestDto, final LocalDate registerDate
    ) {
        String inviteCode = inviteCodeUtil.generate().toUpperCase();
        Period period = Period.between(registerDate, requestDto.getEndDate());
        Room room = requestDto.toEntity(inviteCode, period.getDays());
        return roomAppender.append(room);
    }

    public Room enterRoom(final String inviteCode) {
        Room room = roomFinder.findByInviteCode(inviteCode);
        room.addMemberCount();
        return room;
    }

    public Room findRoom(Long roomId) {
        return roomFinder.find(roomId);
    }

    public Room findRoomByInviteCode(String inviteCode) {
        return roomFinder.findByInviteCode(inviteCode);
    }


}
