package com.dnd.domain.room.repository;

import com.dnd.domain.member.entity.Member;
import com.dnd.domain.room.entity.Room;
import com.dnd.domain.room.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomMemberJpaRepository extends JpaRepository<RoomMember, Long> {

    @Query("select rm from RoomMember rm join fetch rm.member where rm.room = :room")
    List<RoomMember> findRoomMembers(final Room room);

    Optional<RoomMember> findRoomMemberByMemberAndRoom(final Member member, final Room room);

    boolean existsByMemberAndRoom(final Member member, final Room room);
}
