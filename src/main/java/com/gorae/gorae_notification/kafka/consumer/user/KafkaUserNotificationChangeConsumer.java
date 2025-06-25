package com.gorae.gorae_notification.kafka.consumer.user;

import com.gorae.gorae_notification.kafka.consumer.user.dto.UserNotificationChangeEvent;
import com.gorae.gorae_notification.kafka.consumer.user.service.UserNotificationChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUserNotificationChangeConsumer {

    private final UserNotificationChangeService userNotificationChangeService;

    @KafkaListener(
            topics = UserNotificationChangeEvent.Topic,
            groupId = "user-notification-Change",
            containerFactory = "userChangeKafkaListenerFactory"
    )
    public void handleChangeUserEvent(UserNotificationChangeEvent event) {
        try {
            log.info("Kafka 유저 이벤트 변경 수신: userId={}", event.getUserId());
            userNotificationChangeService.processUserChangeEvent(event);
        } catch (Exception e) {
            log.error("유저 이벤트 처리중 오류 발생: {}", e.getMessage(), e);
        }
    }
}
