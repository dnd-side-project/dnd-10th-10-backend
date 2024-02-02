package com.dnd.room;

import com.dnd.common.entity.BaseTimeEntity;
import com.dnd.room.vo.RestrictApp;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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
    private String goalMessage;

    @Column(nullable = false)
    private int personnel;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String inviteCode;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private boolean isFinished;

    @Enumerated(STRING)
    @Column(nullable = false)
    private RestrictApp restrictApp;

    @Column(nullable = false)
    private int limitHour;

    @Builder
    public Room(
            final String title, final String goalMessage,
            final int personnel, final LocalDate startDate,
            final LocalDate endDate, final boolean isActive,
            final boolean isFinished, final RestrictApp restrictApp,
            final int limitHour
    ) {
        this.title = title;
        this.goalMessage = goalMessage;
        this.personnel = personnel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.inviteCode = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        this.isActive = isActive;
        this.isFinished = isFinished;
        this.restrictApp = restrictApp;
        this.limitHour = limitHour;
    }
}
