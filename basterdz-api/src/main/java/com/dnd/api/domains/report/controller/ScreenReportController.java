package com.dnd.api.domains.report.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.api.auth.LoginMember;
import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.report.dto.ScreenReportRequest;
import com.dnd.api.domains.report.service.ScreenReportService;
import com.dnd.domain.member.Member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reports")
public class ScreenReportController implements ScreenReportApiPresentation {

	private final ScreenReportService screenReportService;

	@PostMapping
	public ApiResult<Void> appendScreenReport(
		final @LoginMember Member member,
		final @Valid @RequestBody ScreenReportRequest screenReportRequest
	) {
		screenReportService.updateScreenReport(member, screenReportRequest.toScreenTimes(),
			screenReportRequest.getUsageDate());
		return ApiResult.ok(null);
	}

}
