package com.dnd.domain.report.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class ScreenReport {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "screen_report_id")
    private Long id;

    @Column(nullable = false)
    private String appName;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private LocalDate usageDate;

    @Column(nullable = false)
    private Long memberId;

    public static ScreenReport create(Long memberId, ScreenTime screenTime, LocalDate usageDate) {
        return ScreenReport.builder()
            .memberId(memberId)
            .appName(screenTime.getAppName())
            .duration(screenTime.getDuration())
            .usageDate(usageDate)
            .build();
    }

    public void updateDuration(int duration) {
        this.duration = duration;
    }

}
