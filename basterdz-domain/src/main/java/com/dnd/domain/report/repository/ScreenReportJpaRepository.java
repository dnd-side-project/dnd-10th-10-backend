package com.dnd.domain.report.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dnd.domain.report.entity.ScreenReport;

public interface ScreenReportJpaRepository extends JpaRepository<ScreenReport, Long> {

	List<ScreenReport> findByMemberIdAndUsageDate(Long memberId, LocalDate localDate);
}
