package com.dnd.domain.room.repository;

import com.dnd.domain.room.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMemberJpaRepository extends JpaRepository<RoomMember, Long> {

    List<RoomMember> findByRoomId(final Long roomId);
    boolean existsByMemberId(final Long memberId);
}
