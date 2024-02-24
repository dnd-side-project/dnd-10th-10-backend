package com.dnd.external.fcm;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FcmService {

	public void sendNotification(PushMessage pushMessage) {
		try {
			Message message = Message.builder()
				.setToken(pushMessage.getDeviceToken())
				.setNotification(pushMessage.toNotification())
				.build();
			FirebaseMessaging.getInstance().send(message);
		} catch (FirebaseMessagingException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
