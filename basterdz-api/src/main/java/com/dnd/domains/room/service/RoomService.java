package com.dnd.domains.room.service;

import com.dnd.domains.room.dto.request.RoomCreateRequestDto;
import com.dnd.domains.room.util.InviteCodeUtil;
import com.dnd.room.Room;
import com.dnd.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final InviteCodeUtil inviteCodeUtil;

    @Transactional
    public Room createRoom(final RoomCreateRequestDto requestDto) {
        String inviteCode = inviteCodeUtil.generate();
        Room room = requestDto.toEntity(inviteCode);
        roomRepository.save(room);
        return room;
    }

}
