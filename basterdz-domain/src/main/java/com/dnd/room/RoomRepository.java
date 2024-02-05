package com.dnd.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.inviteCode = :inviteCode")
    Optional<Room> findByInviteCode(@Param("inviteCode")final String inviteCode);
}
