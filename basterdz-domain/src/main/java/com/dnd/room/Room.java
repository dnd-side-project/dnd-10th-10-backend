package com.dnd.room;

import com.dnd.common.entity.BaseTimeEntity;
import com.dnd.room.vo.RestrictApp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

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

}
