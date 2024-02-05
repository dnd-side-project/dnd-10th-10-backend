package com.dnd.domains.room.service;

import com.dnd.domains.room.dto.request.CreateRoomRequestDto;
import com.dnd.domains.room.util.InviteCodeUtil;
import com.dnd.room.Room;
import com.dnd.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final InviteCodeUtil inviteCodeUtil;

    @Transactional
    public Room createRoom(final CreateRoomRequestDto requestDto) {
        String inviteCode = inviteCodeUtil.generate();
        Period period = Period.between(requestDto.getStartDate(), requestDto.getEndDate());
        Room room = requestDto.toEntity(inviteCode, period.getDays());
        roomRepository.save(room);
        return room;
    }

    public Room findRoom(final Long roomId) {
        return roomRepository.findById(roomId).orElseThrow();
    }

    public Room findRoomByInviteCode(final String inviteCode) {
        return roomRepository.findByInviteCode(inviteCode).orElseThrow();
    }
}
