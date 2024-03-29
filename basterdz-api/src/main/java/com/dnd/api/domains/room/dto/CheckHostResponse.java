package com.dnd.api.domains.room.dto;

import static lombok.AccessLevel.PROTECTED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
public class CheckHostResponse {

    @Schema(name = "isHost", example = "true")
    private Boolean isHost;

    public static CheckHostResponse from(boolean isHost) {
        return CheckHostResponse.builder()
                .isHost(isHost)
                .build();
    }
}
