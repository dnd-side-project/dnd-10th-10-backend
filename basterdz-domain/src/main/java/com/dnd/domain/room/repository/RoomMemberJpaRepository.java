package com.dnd.domain.room.repository;

import com.dnd.domain.room.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomMemberJpaRepository extends JpaRepository<RoomMember, Long> {

    boolean existsByMemberId(final Long memberId);
}
