package com.dnd.common.error;

import com.dnd.common.ErrorCode;

import lombok.Getter;

@Getter
public abstract class BasterdzException extends RuntimeException {

	private final String code;
	private final String message;

	public BasterdzException(ErrorCode errorCode) {
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}

}
