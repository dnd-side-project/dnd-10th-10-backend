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
        String code = UUID.randomUUID().toString()
                .replace("-", "").substring(0, 8);
        Room room = requestDto.toEntity(code);
        roomRepository.save(room);
        return room;
    }
}
