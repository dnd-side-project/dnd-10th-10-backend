package com.dnd.notice;

import com.dnd.common.entity.BaseTimeEntity;
import com.dnd.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Booster extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "booster_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_member_id")
    private Member sendMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_member_id")
    private Member receiverMember;

}
