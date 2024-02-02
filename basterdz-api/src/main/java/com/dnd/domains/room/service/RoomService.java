package com.dnd.domains.room.service;

import com.dnd.domains.room.dto.response.RoomCreateResponseDto;
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

    @Transactional
    public RoomCreateResponseDto createRoom(final Room room) {
        roomRepository.save(room);
        return new RoomCreateResponseDto(room);
    }
}
