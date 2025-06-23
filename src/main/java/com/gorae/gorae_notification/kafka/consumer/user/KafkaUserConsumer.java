package com.gorae.gorae_notification.kafka.consumer.user;

import com.gorae.gorae_notification.kafka.consumer.user.dto.UserEvent;
import com.gorae.gorae_notification.kafka.consumer.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUserConsumer {

    private final UserService userService;

    @KafkaListener(
            topics = UserEvent.Topic,
            groupId = "user",
            containerFactory = "userKafkaListenerFactory"
    )
    public void handleUserEvent(UserEvent event) {
        try {
            log.info("Kafka 유저 이벤트 수신: userId={}, action={}", event.getUserId(), event.getAction());
            userService.saveOrUpdateUser(event);
        } catch (Exception e) {
            log.error("유저 이벤트 처리중 오류 발생: {}", e.getMessage(), e);
        }
    }
}
