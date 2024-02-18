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

	//Member
	MEMBER_NOT_FOUND("MEMBER-01", "존재하지 않는 사용자입니다."),

	//Room
	ROOM_NOT_FOUND("ROOM-01", "존재하지 않는 그룹입니다."),
	INVALID_INVITE_CODE("ROOM-02", "올바르지 않은 초대코드입니다."),
	ALREADY_OVER_PERSONNEL("ROOM-03", "정원을 초과하였습니다."),
	UNSUPPORTED_RESTRICT_APP("ROOM-04", "지원하지 않는 앱입니다."),
	MEMBER_ALREADY_ENTERED("ROOM-05", "이미 방에 입장한 회원입니다."),

	//RoomMember
	ROOM_MEMBER_NOT_FOUND("ROOM-MEMBER-01", "방에 존재하지 않는 회원입니다.");

	//ScreenTIme

	private final String code;

	private final String message;
}
