package com.dnd.domain.room.entity;

import static com.dnd.common.exception.ErrorCode.ALREADY_OVER_PERSONNEL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.dnd.domain.common.entity.BaseTimeEntity;
import com.dnd.common.exception.BadRequestException;
import com.dnd.domain.vo.RestrictApp;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Room extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String goal;

    @Column(nullable = false)
    private int memberCount;

    @Column(nullable = false)
    private int personnel;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String inviteCode;

    @Enumerated(STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @Enumerated(STRING)
    @Column(nullable = false)
    private RestrictApp restrictApp;

    @Column(nullable = false)
    private int limitHour;

    @Column(nullable = false)
    private int remainingDay;

    @Builder
    public Room(
            final Long id, final String title,
            final String goal, final int memberCount,
            final int personnel, final LocalDate startDate,
            final LocalDate endDate, final String inviteCode,
            final RoomStatus status, final RestrictApp restrictApp,
            final int limitHour, final int remainingDay
    ) {
        this.id = id;
        this.title = title;
        this.goal = goal;
        this.memberCount = memberCount;
        this.personnel = personnel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.inviteCode = inviteCode;
        this.status = status;
        this.restrictApp = restrictApp;
        this.limitHour = limitHour;
        this.remainingDay = remainingDay;
    }

    public void addMemberCount() {
        if (memberCount == personnel) {
            throw new BadRequestException(ALREADY_OVER_PERSONNEL);
        }
        this.memberCount += 1;
    }

    public void changeStatus(RoomStatus roomStatus) {
        this.status = roomStatus;
    }
}
