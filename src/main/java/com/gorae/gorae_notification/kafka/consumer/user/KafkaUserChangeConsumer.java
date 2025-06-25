package com.gorae.gorae_notification.kafka.consumer.user;

import com.gorae.gorae_notification.kafka.consumer.user.dto.UserChangeEvent;
import com.gorae.gorae_notification.kafka.consumer.user.service.UserChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUserChangeConsumer {

    private final UserChangeService userChangeService;

    @KafkaListener(
            topics = UserChangeEvent.Topic,
            groupId = "user-notification-Change",
            containerFactory = "userChangeKafkaListenerFactory"
    )
    public void handleChangeUserEvent(UserChangeEvent event) {
        try {
            log.info("Kafka 유저 이벤트 변경 수신: userId={}", event.getUserId());
            userChangeService.processUserChangeEvent(event);
        } catch (Exception e) {
            log.error("유저 이벤트 처리중 오류 발생: {}", e.getMessage(), e);
        }
    }
}
