package com.dnd.api.domains.report.controller;

import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.report.dto.ScreenReportRequest;
import com.dnd.domain.member.entity.Member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "\uD83D\uDCC4Report", description = "Report API")
public interface ScreenReportApiPresentation {

	@Operation(summary = "하루 스크린 리포트 전송")
	@ApiResponse(responseCode = "200", description = "스크린 리포트 요청 성공")
	ApiResult<Void> appendScreenReport(@Schema(hidden = true) Member member, ScreenReportRequest screenReportRequest);
}
