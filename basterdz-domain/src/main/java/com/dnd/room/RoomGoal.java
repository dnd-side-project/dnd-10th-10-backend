package com.dnd.room;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class RoomGoal {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "room_goal_id")
    private Long id;

    @Column(nullable = false)
    private String appName;

    @Column(nullable = false)
    private LocalDateTime limitHour;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

}
