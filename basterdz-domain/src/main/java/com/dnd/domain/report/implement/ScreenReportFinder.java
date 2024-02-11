package com.dnd.domain.report.implement;

import java.time.LocalDate;
import java.util.List;

import com.dnd.domain.common.annotation.Finder;
import com.dnd.domain.report.entity.ScreenReport;
import com.dnd.domain.report.repository.ScreenReportJpaRepository;

import lombok.RequiredArgsConstructor;

@Finder
@RequiredArgsConstructor
public class ScreenReportFinder {

	private final ScreenReportJpaRepository screenReportJpaRepository;

	public List<ScreenReport> find(Long memberId, LocalDate usageTime) {
		return screenReportJpaRepository.findByMemberIdAndUsageDate(memberId, usageTime);
	}
}
