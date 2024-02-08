package com.dnd.domains.room.service;

import com.dnd.api.domains.room.dto.request.CreateRoomRequestDto;
import com.dnd.api.domains.room.service.RoomService;
import com.dnd.api.domains.room.util.InviteCodeUtil;
import com.dnd.domain.room.Room;
import com.dnd.domain.room.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static com.dnd.domain.room.vo.RestrictApp.INSTAGRAM;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class RoomServiceTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @MockBean
    private InviteCodeUtil inviteCodeUtil;

    @AfterEach
    void tearDown() {
        roomRepository.deleteAllInBatch();
    }

    @DisplayName("초기 방을 생성할 수 있습니다.")
    @Test
    void createRoom(){
        // given
        CreateRoomRequestDto requestDto = CreateRoomRequestDto.builder()
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
