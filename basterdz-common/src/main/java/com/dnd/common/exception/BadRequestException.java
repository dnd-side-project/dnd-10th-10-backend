package com.dnd.common.exception;

public class BadRequestException extends BasterdzException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
