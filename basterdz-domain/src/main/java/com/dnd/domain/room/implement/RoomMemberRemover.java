package com.dnd.domain.room.implement;

import com.dnd.domain.common.annotation.Remover;
import com.dnd.domain.room.repository.RoomMemberJpaRepository;
import lombok.RequiredArgsConstructor;

@Remover
@RequiredArgsConstructor
public class RoomMemberRemover {

    private final RoomMemberJpaRepository roomMemberJpaRepository;

    public void deleteAll() {
        roomMemberJpaRepository.deleteAllInBatch();
    }
}
