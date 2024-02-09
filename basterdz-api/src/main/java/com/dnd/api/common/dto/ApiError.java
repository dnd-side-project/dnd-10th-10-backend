package com.dnd.api.common.dto;


import com.dnd.common.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

	private final String code;
	private final String message;

	public ApiError(ErrorCode errorCode) {
		this(errorCode.getCode(), errorCode.getMessage());
	}

	public ApiError(ErrorCode errorCode, String message) {
		this(errorCode.getCode(), message);
	}

}
