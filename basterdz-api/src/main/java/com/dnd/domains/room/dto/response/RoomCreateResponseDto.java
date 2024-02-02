package com.dnd.domains.room.dto.response;

import com.dnd.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class RoomCreateResponseDto {

    private Long id;
    private String title;
    private int personnel;

    @Builder
    public RoomCreateResponseDto(Room room) {
        this.id = room.getId();
        this.title = room.getTitle();
        this.personnel = room.getPersonnel();
    }
}
