package com.dnd.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	SAMPLE_ERROR("SAMPLE-01", "Sample Error Message");

	private final String code;
	private final String message;
}
