package com.dnd.api.domains.report.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScreenTimeRequest {

	@Schema(description = "앱 이름", example = "INSTAGRAM")
	private String appName;

	@Schema(description = "지속시간(분)", example = "120")
	private int duration;

}
