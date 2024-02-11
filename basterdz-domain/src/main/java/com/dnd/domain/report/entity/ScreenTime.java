package com.dnd.domain.report.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ScreenTime {

	private String appName;

	private int duration;

	public static ScreenTime of(String appName, int duration) {
		return ScreenTime.builder()
			.appName(appName)
			.duration(duration)
			.build();
	}
}
