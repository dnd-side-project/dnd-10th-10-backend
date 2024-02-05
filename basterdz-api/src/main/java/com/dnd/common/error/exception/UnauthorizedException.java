package com.dnd.common.error.exception;

import com.dnd.common.error.ErrorCode;
import com.dnd.common.error.exception.BasterdzException;

public class UnauthorizedException extends BasterdzException {
	public UnauthorizedException(ErrorCode errorCode) {
		super(errorCode);
	}
}
