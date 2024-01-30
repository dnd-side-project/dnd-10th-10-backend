package com.dnd.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.common.dto.ApiResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/sample")
public class SampleController {

	@GetMapping("/success")
	public ApiResult<SampleResponseDto> sampleSuccessApi(@RequestBody @Valid SampleRequestDto sampleRequestDto) {
		return ApiResult.ok(new SampleResponseDto(1L, "content"));
	}

	@GetMapping("/fail")
	public ApiResult<SampleResponseDto> sampleErrorApi() {
		throw new IllegalArgumentException("");
	}

}
