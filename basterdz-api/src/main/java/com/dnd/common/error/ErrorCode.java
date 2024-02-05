package com.dnd.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	//common
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON-01", ""),
	METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "COMMON-02", ""),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON-03", ""),
	FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON-04", ""),
	NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON-05", ""),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON-06", ""),
	UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "COMMON-07", ""),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-08", "");

	private final HttpStatus status;

	private final String code;

	private final String message;
}
