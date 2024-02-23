package com.dnd.domain.ranking;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class RankingReport {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ranking_report_id")
    private Long id;

    @Column(nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private int ranking;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private LocalDate usageDate;

}
