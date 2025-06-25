package com.gorae.gorae_notification.kafka.consumer.post;

import com.gorae.gorae_notification.kafka.consumer.post.dto.AdoptNotificationEvent;
import com.gorae.gorae_notification.kafka.consumer.post.service.AdoptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaAdoptNotificationConsumer {

    private final AdoptService adoptService;

    @KafkaListener(
            topics = AdoptNotificationEvent.Topic,
            groupId = "adopt",
            containerFactory = "adoptKafkaListenerFactory"
    )
    public void handleAdoptEvent(AdoptNotificationEvent event) {
        try {
            log.info("userId={}님이 채택했습니다.", event.getPostUserId());
            adoptService.processAdoptEvent(event);
        } catch (Exception e) {
            log.error("채택중 오류가 발생헀습니다. {}", e.getMessage(), e);
        }
    }
}
