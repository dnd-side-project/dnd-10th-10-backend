package com.dnd.api.common.dto;

import com.dnd.common.exception.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResult<T> {

	@Schema(name = "success", example = "true")
	private final boolean success;
	private final T data;
	@Schema(name = "error", example = "null")
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
