package com.gorae.gorae_notification.kafka.consumer.post;

import com.gorae.gorae_notification.kafka.consumer.post.dto.AdoptChangeEvent;
import com.gorae.gorae_notification.kafka.consumer.post.service.AdoptChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaAdoptChangeConsumer {

    private final AdoptChangeService adoptChangeService;

    @KafkaListener(
            topics = AdoptChangeEvent.Topic,
            groupId = "adopt-change",
            containerFactory = "adoptChangeKafkaListenerFactory"
    )
    public void handleAdoptChangeEvent(AdoptChangeEvent event) {
        try {
            log.info("userId={}님이 채택을 취소했습니다.", event.getPostUserId());
            adoptChangeService.processAdoptChangeEvent(event);
        } catch (Exception e) {
            log.error("채택중 오류가 발생헀습니다. {}", e.getMessage(), e);
        }
    }
}
