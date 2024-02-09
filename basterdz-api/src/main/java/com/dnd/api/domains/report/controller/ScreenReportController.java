package com.dnd.api.domains.report.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.report.dto.ScreenReportRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reports")
public class ScreenReportController implements ScreenReportApiPresentation{

	@PostMapping
	public ApiResult<Void> appendScreenReport(
		final @Valid @RequestBody ScreenReportRequest screenReportRequest
	) {
		return ApiResult.ok(null);
	}

}
