package com.dnd.common.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiError {

	private final int status;
	private final String code;
	private final String message;

	public ApiError(HttpStatus status, Throwable throwable) {
		this(status, null, throwable.getMessage());
	}

	public ApiError(HttpStatus status, String code, String message) {
		this.status = status.value();
		this.code = code;
		this.message = message;
	}
}
