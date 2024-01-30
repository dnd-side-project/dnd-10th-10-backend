package com.dnd.room;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private boolean isHost;

}
