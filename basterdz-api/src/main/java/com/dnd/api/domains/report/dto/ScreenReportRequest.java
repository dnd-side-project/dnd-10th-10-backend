package com.dnd.api.domains.report.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dnd.domain.report.entity.ScreenTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScreenReportRequest {

	@Schema(description = "사용 날짜", example = "2024-02-03")
	private LocalDate usageDate;

	private List<ScreenTimeRequest> screenTimeData;

	public List<ScreenTime> toScreenTimes() {
		return screenTimeData.stream()
			.map(screenTime -> ScreenTime.of(screenTime.getAppName(), screenTime.getDuration()))
			.collect(Collectors.toList());
	}
}
