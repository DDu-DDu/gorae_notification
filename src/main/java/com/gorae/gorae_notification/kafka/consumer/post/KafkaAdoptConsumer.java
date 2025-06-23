package com.gorae.gorae_notification.kafka.consumer.post;

import com.gorae.gorae_notification.kafka.consumer.post.dto.AdoptEvent;
import com.gorae.gorae_notification.kafka.consumer.post.dto.CommentEvent;
import com.gorae.gorae_notification.kafka.consumer.post.service.AdoptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaAdoptConsumer {

    private final AdoptService adoptService;

    @KafkaListener(
            topics = CommentEvent.Topic,
            groupId = "adopt",
            containerFactory = "adoptKafkaListenerFactory"
    )
    public void handleAdoptEvent(AdoptEvent event, Acknowledgment ack) {
        try {
            log.info("userId={}님이 채택했습니다.", event.getPostUserId());
            adoptService.processAdoptCommentEvent(event);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("채택중 오류가 발생헀습니다. {}", e.getMessage(), e);
        }
    }
}
