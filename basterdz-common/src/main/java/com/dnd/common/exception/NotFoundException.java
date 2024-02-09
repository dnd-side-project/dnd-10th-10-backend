package com.dnd.common.exception;

public class NotFoundException extends BasterdzException {
	public NotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
