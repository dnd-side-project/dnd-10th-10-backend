package com.dnd.domain.room.repository;

import static com.dnd.domain.member.entity.QMember.member;
import static com.dnd.domain.report.entity.QScreenReport.screenReport;
import static com.dnd.domain.room.entity.QRoom.room;
import static com.dnd.domain.room.entity.QRoomMember.roomMember;
import static com.dnd.domain.room.entity.RoomStatus.ACTIVE;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.time.LocalDate;
import java.util.List;

import com.dnd.domain.room.dto.ActiveRoom;
import com.dnd.domain.vo.RestrictApp;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Repository;

import com.dnd.domain.room.dto.RoomMemberDailyScreenTime;
import com.dnd.domain.room.dto.RoomMemberRankingDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomMemberQueryRepository {

	private final JPAQueryFactory queryFactory;

	public List<RoomMemberRankingDto> searchRoomMemberRankingByUsageDate(LocalDate usageDate) {
		return queryFactory.select(
				Projections.constructor(
					RoomMemberRankingDto.class,
					roomMember.room.id,
					roomMember.member.id,
					room.restrictApp.stringValue(),
					Expressions.stringTemplate("rank() over (partition by {0} order by {1})", room.id,
						screenReport.duration).as("ranking"),
					screenReport.duration,
					screenReport.usageDate
				)
			)
			.from(roomMember)
			.join(room).on(roomMember.room.id.eq(room.id))
			.join(screenReport).on(roomMember.member.id.eq(screenReport.memberId)
				.and(room.restrictApp.stringValue().eq(screenReport.appName))
			)
			.where(
				room.status.eq(ACTIVE),
				screenReport.usageDate.eq(usageDate)
			)
			.fetch();
	}

	public List<RoomMemberDailyScreenTime> findRoomMemberScreenTimeByRoomIdAndLocalDate(Long roomId, LocalDate usageDate) {
		return queryFactory.select(
						Projections.constructor(
								RoomMemberDailyScreenTime.class,
								roomMember.member.id,
								roomMember.member.nickname,
								room.limitHour,
								screenReport.duration.coalesce(0)
						)
				)
				.from(roomMember)
				.join(room).on(room.id.eq(roomMember.room.id))
				.join(member).on(member.id.eq(roomMember.member.id))
				.leftJoin(screenReport)
				.on(roomMember.member.id.eq(screenReport.memberId)
						.and(room.restrictApp.stringValue().eq(screenReport.appName.stringValue()))
						.and(screenReport.usageDate.eq(usageDate)))
				.where(roomMember.room.id.eq(roomId))
				.fetch();
	}

	// TODO: 최신 순 구현하기
	public List<ActiveRoom> findActiveRoomsByMember(final Long memberId, final RestrictApp restrictApp) {
		return queryFactory.select(
						Projections.constructor(
								ActiveRoom.class,
								room.id,
								room.title,
								room.personnel,
								room.restrictApp,
								room.memberCount
						)
				)
				.from(roomMember)
				.join(member).on(member.id.eq(roomMember.member.id).and(member.id.eq(memberId)))
				.join(room).on(room.id.eq(roomMember.room.id))
				.where(restrictAppEq(restrictApp),
						room.status.eq(ACTIVE)
				)
				.fetch();
	}

	private BooleanExpression restrictAppEq(final RestrictApp restrictApp) {
		return isEmpty(restrictApp) ? null : room.restrictApp.eq(restrictApp);
	}
}
