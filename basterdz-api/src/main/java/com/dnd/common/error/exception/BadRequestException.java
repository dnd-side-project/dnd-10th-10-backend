package com.dnd.common.error.exception;

import com.dnd.common.error.ErrorCode;

public class BadRequestException extends BasterdzException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
