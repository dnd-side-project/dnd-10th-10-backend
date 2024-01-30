package com.dnd.room;

import com.dnd.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String resolution_message;

    @Column(nullable = false)
    private int maxHeadCount;

    @Column(nullable = false)
    private LocalDateTime start_date;

    @Column(nullable = false)
    private LocalDateTime end_date;

    @Column(nullable = false)
    private String invitationCode;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private boolean isFinished;

}
