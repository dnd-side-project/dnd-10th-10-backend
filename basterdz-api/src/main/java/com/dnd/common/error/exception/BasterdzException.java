package com.dnd.common.error.exception;

import com.dnd.common.error.ErrorCode;

import lombok.Getter;

@Getter
public abstract class BasterdzException extends RuntimeException {

	private final ErrorCode errorCode;

	public BasterdzException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
