package com.dnd.report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class DailyReport {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "daily_report_id")
    private Long id;

    @Column(nullable = false)
    private String appName;

    @Column(nullable = false)
    private LocalDateTime UsageTime;

    @Column(nullable = false)
    private LocalDateTime UsageDate;
}
