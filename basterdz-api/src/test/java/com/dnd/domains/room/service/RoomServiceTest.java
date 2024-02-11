package com.dnd.domains.room.service;

import com.dnd.api.domains.room.dto.CreateRoomRequest;
import com.dnd.api.domains.room.service.RoomService;
import com.dnd.api.domains.room.util.InviteCodeUtil;
import com.dnd.domain.room.entity.Room;
import com.dnd.domain.room.implement.RoomRemover;
import com.dnd.config.IntegrationServiceTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static com.dnd.domain.vo.RestrictApp.INSTAGRAM;
import static org.mockito.BDDMockito.given;

class RoomServiceTest extends IntegrationServiceTest {

    @Autowired
    private RoomRemover roomRemover;

    @Autowired
    private RoomService roomService;

    @MockBean
    private InviteCodeUtil inviteCodeUtil;

    @AfterEach
    void tearDown() {
        roomRemover.deleteAll();
    }

    @DisplayName("초기 방을 생성할 수 있습니다.")
    @Test
    void createRoom(){
        // given
        CreateRoomRequest requestDto = CreateRoomRequest.builder()
                .title("우리들의 도파민 탈출기")
                .goal("우리 시험기간에만 인스타 하지 말아보자")
                .personnel(4)
                .restrictApp(INSTAGRAM)
                .startDate(LocalDate.of(2024, 1, 23))
                .endDate(LocalDate.of(2024, 1, 25))
                .limitHour(1)
                .build();

        Room createdRoom = requestDto.toEntity("ABCDEFG", 2);

        LocalDate registeredDate = LocalDate.of(2024, 1, 23);

        // when
        given(inviteCodeUtil.generate()).willReturn("ABCDEFG");

        Room result = roomService.createRoom(requestDto, registeredDate);

        // then
        Assertions.assertThat(createdRoom.getInviteCode())
                .isEqualTo(result.getInviteCode());
    }

}
