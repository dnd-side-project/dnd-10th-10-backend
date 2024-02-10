package com.dnd.domain.report;

import com.dnd.domain.room.entity.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class RankingReport {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ranking_report_id")
    private Long id;

    @Column(nullable = false)
    private int memberRank;

    @Column(nullable = false)
    private String memberNickname;

    @Column(nullable = false)
    private LocalDateTime dailyUsageTime;

    @Column(nullable = false)
    private LocalDateTime rankingDate;

    @Column(nullable = false)
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

}