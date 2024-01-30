package com.dnd.common.dto;

import org.springframework.http.HttpStatus;

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

	public static ApiResult<?> error(HttpStatus status, Throwable throwable) {
		return new ApiResult<>(false, null, new ApiError(status, throwable));
	}

	public static ApiResult<?> error(HttpStatus status, String code, String message) {
		return new ApiResult<>(false, null, new ApiError(status, code, message));
	}
}
