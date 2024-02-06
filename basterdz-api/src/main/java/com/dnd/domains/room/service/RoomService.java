package com.dnd.domains.room.service;

import com.dnd.common.error.exception.NotFoundException;
import com.dnd.domains.room.dto.request.CreateRoomRequestDto;
import com.dnd.domains.room.util.InviteCodeUtil;
import com.dnd.room.Room;
import com.dnd.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

import static com.dnd.common.error.ErrorCode.NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final InviteCodeUtil inviteCodeUtil;

    @Transactional
    public Room createRoom(
            final CreateRoomRequestDto requestDto, final LocalDate registerDate
    ) {
        String inviteCode = inviteCodeUtil.generate();
        Period period = Period.between(registerDate, requestDto.getEndDate());
        Room room = requestDto.toEntity(inviteCode, period.getDays());
        roomRepository.save(room);
        return room;
    }

    public Room findRoom(final Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    public Room findRoomByInviteCode(final String inviteCode) {
        return roomRepository.findByInviteCode(inviteCode)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }
}
