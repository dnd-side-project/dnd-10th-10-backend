package com.dnd.api.domains.report.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScreenTimeRequest {
	@Schema(description = "앱 번들", example = "1")
	private Long bundle;

	@Schema(description = "앱 이름", example = "인스타그램")
	private String appName;

	@Schema(description = "지속시간(분)", example = "120")
	private int duration;

}
