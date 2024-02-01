package com.dnd.common.error;

import com.dnd.common.ErrorCode;

public class NotFoundException extends BasterdzException {

	public NotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
