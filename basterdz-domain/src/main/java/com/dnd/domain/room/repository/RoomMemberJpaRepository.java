package com.dnd.domain.room.repository;

import com.dnd.domain.member.entity.Member;
import com.dnd.domain.room.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomMemberJpaRepository extends JpaRepository<RoomMember, Long> {

    @Query("select rm.member from RoomMember rm where rm.member.id = :memberId")
    Optional<Member> existsMemberByMemberId(@Param("memberId") final Long memberId);
}
