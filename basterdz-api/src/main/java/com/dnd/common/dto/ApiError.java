package com.dnd.common.dto;

import com.dnd.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

	private final int status;
	private final String code;
	private final String message;

	public ApiError(ErrorCode errorCode) {
		this(errorCode.getStatus().value(), errorCode.getCode(), errorCode.getMessage());
	}

	public ApiError(ErrorCode errorCode, String message) {
		this(errorCode.getStatus().value(), errorCode.getCode(), message);
	}

}
