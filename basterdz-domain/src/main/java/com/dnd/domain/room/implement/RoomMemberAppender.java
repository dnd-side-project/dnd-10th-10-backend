package com.dnd.domain.room.implement;

import com.dnd.domain.common.annotation.Appender;
import com.dnd.domain.room.repository.RoomMemberJpaRepository;
import com.dnd.domain.room.entity.RoomMember;
import lombok.RequiredArgsConstructor;

@Appender
@RequiredArgsConstructor
public class RoomMemberAppender {

    private final RoomMemberJpaRepository roomMemberJpaRepository;

    public void append(final RoomMember roomMember) {
        roomMemberJpaRepository.save(roomMember);
    }
}
