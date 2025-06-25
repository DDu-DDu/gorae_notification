package com.gorae.gorae_notification.kafka.consumer.user;

import com.gorae.gorae_notification.kafka.consumer.user.dto.UserNotificationEvent;
import com.gorae.gorae_notification.kafka.consumer.user.service.UserNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUserNotificationConsumer {

    private final UserNotificationService userNotificationService;

    @KafkaListener(
            topics = UserNotificationEvent.Topic,
            groupId = "user-notification",
            containerFactory = "userKafkaListenerFactory"
    )
    public void handleUserEvent(UserNotificationEvent event) {
        try {
            log.info("Kafka 유저 이벤트 수신: userId={}", event.getUserId());
            userNotificationService.processUserEvent(event);
        } catch (Exception e) {
            log.error("유저 이벤트 처리중 오류 발생: {}", e.getMessage(), e);
        }
    }
}
