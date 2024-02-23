package com.dnd.domain.ranking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingReportRepository extends JpaRepository<RankingReport, Long> {
	List<RankingReport> findRankingReportByRoomIdAndUsageDate(Long userId, LocalDate localDate);
}
