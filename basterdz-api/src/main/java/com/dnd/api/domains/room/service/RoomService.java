package com.dnd.api.domains.room.service;

import static com.dnd.common.exception.ErrorCode.INVALID_INVITE_CODE;
import static com.dnd.common.exception.ErrorCode.ROOM_NOT_FOUND;

import com.dnd.api.domains.room.util.InviteCodeUtil;
import com.dnd.common.exception.BadRequestException;
import com.dnd.common.exception.NotFoundException;
import com.dnd.api.domains.room.dto.request.CreateRoomRequestDto;
import com.dnd.domain.room.Room;
import com.dnd.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

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
        String inviteCode = inviteCodeUtil.generate().toUpperCase();
        Period period = Period.between(registerDate, requestDto.getEndDate());
        Room room = requestDto.toEntity(inviteCode, period.getDays());
        return roomRepository.save(room);
    }

    @Transactional
    public Room enterRoom(final String inviteCode) {
        Room room = findRoomByInviteCode(inviteCode);
        room.addMemberCount();
        return roomRepository.save(room);
    }

    public Room findRoom(final Long roomId) {
        return roomRepository.findById(roomId)
            .orElseThrow(() -> new NotFoundException(ROOM_NOT_FOUND));
    }

    public Room findRoomByInviteCode(final String inviteCode) {
        return roomRepository.findByInviteCode(inviteCode)
            .orElseThrow(() -> new BadRequestException(INVALID_INVITE_CODE));
    }
}
