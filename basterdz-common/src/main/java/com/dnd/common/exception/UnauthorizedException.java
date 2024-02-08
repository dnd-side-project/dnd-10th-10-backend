package com.dnd.common.exception;

public class UnauthorizedException extends BasterdzException {
	public UnauthorizedException(ErrorCode errorCode) {
		super(errorCode);
	}
}
