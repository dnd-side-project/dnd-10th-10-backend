package com.dnd.api.domains.room.controller;

import com.dnd.api.auth.LoginMember;
import com.dnd.api.common.dto.ApiResult;
import com.dnd.api.domains.room.dto.CreateRoomRequest;
import com.dnd.api.domains.room.dto.EnterRoomRequest;
import com.dnd.api.domains.room.dto.RoomIdResponse;
import com.dnd.api.domains.room.service.RoomService;
import com.dnd.domain.member.Member;
import com.dnd.domain.room.entity.Room;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomMemberController implements RoomMemberApiPresentation{

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<ApiResult<RoomIdResponse>> createRoom(
            final @LoginMember Member member,
            final @Valid @RequestBody CreateRoomRequest roomCreateRequestDto
    ) {
        LocalDate registeredDate = LocalDate.now();
        Room createdRoom = roomService.createRoom(roomCreateRequestDto, registeredDate, member);
        RoomIdResponse responseDto = RoomIdResponse.from(createdRoom);
        return ResponseEntity.status(CREATED).body(ApiResult.ok(responseDto));
    }

    @PostMapping("/entrance")
    public ApiResult<RoomIdResponse> enterRoom(
            final @LoginMember Member member,
            final @RequestBody @Valid EnterRoomRequest enterRoomRequestDto
    ) {
        String inviteCode = enterRoomRequestDto.getInviteCode();
        Room room = roomService.enterRoom(inviteCode, member);
        return ApiResult.ok(RoomIdResponse.from(room));
    }
}
