package com.dnd.common.error.exception;

import com.dnd.common.error.ErrorCode;
import com.dnd.common.error.exception.BasterdzException;

public class NotFoundException extends BasterdzException {

	public NotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
