package com.dnd.domains.room.service;

import com.dnd.domains.room.dto.request.RoomCreateRequestDto;
import com.dnd.room.Room;
import com.dnd.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    public Room createRoom(final RoomCreateRequestDto requestDto) {
        String inviteCode = createInviteCode();
        Room room = requestDto.toEntity(inviteCode);
        roomRepository.save(room);
        return room;
    }

    private String createInviteCode() {
        return UUID.randomUUID().toString()
                .replace("-", "").substring(0, 8);
    }
}
