package com.dnd.common.error;

import com.dnd.common.ErrorCode;

public class UnauthorizedException extends BasterdzException {
	public UnauthorizedException(ErrorCode errorCode) {
		super(errorCode);
	}
}
