package com.dnd.api.domains.report.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnd.domain.member.Member;
import com.dnd.domain.report.implement.ScreenReportAppender;
import com.dnd.domain.report.implement.ScreenReportFinder;
import com.dnd.domain.report.entity.ScreenReport;
import com.dnd.domain.report.entity.ScreenTime;
import com.dnd.domain.vo.RestrictApp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScreenReportService {

	private final ScreenReportFinder screenReportFinder;
	private final ScreenReportAppender screenReportAppender;

	@Transactional
	public void updateScreenReport(final Member member, final List<ScreenTime> screenTimeData,
		final LocalDate usageDate) {
		List<ScreenTime> screenTimes = filterToRestrictApp(screenTimeData);
		List<ScreenReport> screenReports = screenReportFinder.find(member.getId(), usageDate);
		List<ScreenTime> appendedScreenTime = new ArrayList<>();
		Map<String, ScreenReport> existingScreenReport = createAppNameMap(screenReports);
		screenTimes.forEach(screenTime -> {
			if(existingScreenReport.containsKey(screenTime.getAppName())) {
				ScreenReport screenReport = existingScreenReport.get(screenTime.getAppName());
				screenReport.updateDuration(screenTime.getDuration());
			} else {
				appendedScreenTime.add(screenTime);
			}
		});
		appendScreenTimes(member.getId(), appendedScreenTime, usageDate);
	}

	public void appendScreenTimes(Long memberId, List<ScreenTime> appendedScreenTIme, LocalDate usageDate) {
		screenReportAppender.appendAll(memberId, appendedScreenTIme, usageDate);
	}

	private List<ScreenTime> filterToRestrictApp(List<ScreenTime> screenTimeData) {
		return screenTimeData.stream()
			.filter(screenTime -> RestrictApp.contains(screenTime.getAppName()))
			.toList();
	}

	private Map<String, ScreenReport> createAppNameMap(List<ScreenReport> screenReports) {
		return screenReports.stream()
			.collect(Collectors.toMap(ScreenReport::getAppName, Function.identity()));
	}
}
