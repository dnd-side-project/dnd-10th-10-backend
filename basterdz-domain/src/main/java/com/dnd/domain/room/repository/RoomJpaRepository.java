package com.dnd.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.dnd.domain.room.entity.Room;

@Repository
public interface RoomJpaRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.inviteCode = :inviteCode")
    Optional<Room> findByInviteCode(@Param("inviteCode")final String inviteCode);

}
