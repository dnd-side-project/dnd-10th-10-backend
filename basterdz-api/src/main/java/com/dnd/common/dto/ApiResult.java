package com.dnd.common.dto;

import com.dnd.common.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResult<T> {

	private final boolean success;
	private final T data;
	private final ApiError error;

	public static <T> ApiResult<T> ok(T data) {
		return new ApiResult<>(true, data, null);
	}

	public static ApiResult<?> error(ErrorCode errorCode) {
		return new ApiResult<>(false, null, new ApiError(errorCode));
	}

	public static ApiResult<?> error(ErrorCode errorCode, String message) {
		return new ApiResult<>(false, null, new ApiError(errorCode, message));
	}

}
