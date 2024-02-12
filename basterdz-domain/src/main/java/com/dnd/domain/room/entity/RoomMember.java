package com.dnd.domain.room.entity;

import com.dnd.common.exception.BadRequestException;
import com.dnd.domain.member.entity.Member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static com.dnd.common.exception.ErrorCode.INVALID_HOST;
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

    public void isEqualsMember(final Long memberId) {
        if (!Objects.equals(memberId, this.member.getId())) {
            throw new BadRequestException(INVALID_HOST);
        }
    }
}
