package com.dnd.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	//common
	BAD_REQUEST("COMMON-01", " "),
	METHOD_ARGUMENT_NOT_VALID("COMMON-02", ""),
	UNAUTHORIZED( "COMMON-03", ""),
	FORBIDDEN("COMMON-04", ""),
	METHOD_NOT_ALLOWED("COMMON-05", ""),
	UNSUPPORTED_MEDIA_TYPE("COMMON-06", ""),
	INTERNAL_SERVER_ERROR("COMMON-07", ""),

	//Room
	ROOM_NOT_FOUND("ROOM-01", "존재하지 않는 그룹입니다."),
	INVALID_INVITE_CODE("ROOM-02", "올바르지 않은 초대코드입니다."),
	ALREADY_OVER_PERSONNEL("ROOM-03", "정원을 초과하였습니다");

	private final String code;

	private final String message;
}
