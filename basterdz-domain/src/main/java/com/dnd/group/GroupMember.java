package com.dnd.group;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class GroupMember {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "group_member_id")
    private Long id;

    @Column(nullable = false)
    private String appName;

    @Column(nullable = false)
    private LocalDateTime limitHour;
}
