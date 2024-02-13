package com.dnd.domain.room.repository;

import com.dnd.domain.room.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomMemberJpaRepository extends JpaRepository<RoomMember, Long> {

    @Query("select rm from RoomMember rm where rm.member.id = :memberId and rm.room.id = :roomId")
    RoomMember findByRoomMember(final Long memberId, final Long roomId);

    boolean existsByMemberId(final Long memberId);
}
