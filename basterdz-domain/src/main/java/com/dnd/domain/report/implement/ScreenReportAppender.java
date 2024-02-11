package com.dnd.domain.report.implement;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dnd.domain.common.annotation.Appender;
import com.dnd.domain.report.entity.ScreenReport;
import com.dnd.domain.report.entity.ScreenTime;
import com.dnd.domain.report.repository.ScreenReportJpaRepository;

import lombok.RequiredArgsConstructor;

@Appender
@RequiredArgsConstructor
public class ScreenReportAppender {

	private final ScreenReportJpaRepository screenReportJpaRepository;

	public void appendAll(final Long memberId, final List<ScreenTime> screenTimes, final LocalDate usageTime) {
		List<ScreenReport> screenReports = createScreenReports(memberId, screenTimes, usageTime);
		screenReportJpaRepository.saveAll(screenReports);
	}

	public static List<ScreenReport> createScreenReports(final Long memberId, final List<ScreenTime> screenTimes, final LocalDate usageTime) {
		return screenTimes.stream()
			.map(screenTime -> ScreenReport.create(memberId, screenTime, usageTime))
			.collect(Collectors.toList());
	}
}
