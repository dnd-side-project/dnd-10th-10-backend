package com.dnd.external.fcm;

import com.google.firebase.messaging.Notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PushMessage {

	private String deviceToken;

	private String title;

	private String content;

	public Notification toNotification() {
		return Notification.builder()
			.setTitle(title)
			.setBody(content)
			.build();
	}

	public static PushMessage of(String deviceToken, String title, String content) {
		return PushMessage.builder()
			.deviceToken(deviceToken)
			.title(title)
			.content(content)
			.build();
	}


}
