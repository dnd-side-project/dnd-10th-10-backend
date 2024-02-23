package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import com.dnd.domain.room.entity.Room;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class CreateRoomResponse {

	@Schema(name = "id", example = "1")
	private Long id;

	@Schema(name = "inviteCode", example = "1")
	private String inviteCode;

	public static CreateRoomResponse from(final Room room) {
		return CreateRoomResponse.builder()
			.id(room.getId())
			.inviteCode(room.getInviteCode())
			.build();
	}
}
