package com.dnd.domain.room.entity;

import com.dnd.domain.member.Member;
import com.dnd.domain.room.entity.Room;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class RoomMember {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "room_member_id")
    private Long id;

    @Column(nullable = false)
    private boolean isHost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public static RoomMember of(final Member member, final Room room, final boolean isHost) {
        return RoomMember.builder()
                .isHost(isHost)
                .member(member)
                .room(room)
                .build();
    }
}
