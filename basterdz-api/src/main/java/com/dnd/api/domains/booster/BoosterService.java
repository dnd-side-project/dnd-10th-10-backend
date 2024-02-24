package com.dnd.api.domains.booster;

import org.springframework.stereotype.Service;

import com.dnd.domain.member.entity.Member;
import com.dnd.domain.member.implement.MemberFinder;
import com.dnd.external.fcm.FcmService;
import com.dnd.external.fcm.PushMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoosterService {

	private final MemberFinder memberFinder;

	private final FcmService fcmService;

	public void sendPushMessage(Long targetId, String content) {
		Member member = memberFinder.find(targetId);
		fcmService.sendNotification(PushMessage.of(member.getDeviceToken(), "Basterdz", content));
	}

}
