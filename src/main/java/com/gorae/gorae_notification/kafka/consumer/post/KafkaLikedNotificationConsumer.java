package com.gorae.gorae_notification.kafka.consumer.post;

import com.gorae.gorae_notification.kafka.consumer.post.dto.LikedNotificationEvent;
import com.gorae.gorae_notification.kafka.consumer.post.service.LikedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaLikedNotificationConsumer {

    private final LikedService likedService;

    @KafkaListener(
            topics = LikedNotificationEvent.Topic,
            groupId = "liked",
            containerFactory = "likedKafkaListenerFactory"
    )
    public void handleLikedEvent(LikedNotificationEvent event) {
        try {
            log.info("userId={}님이 좋아요를 남겼습니다.", event.getCommentLikeUserId());
            likedService.processLikedEvent(event);
        } catch (Exception e) {
            log.error("좋아요중 오류가 발생헀습니다. {}", e.getMessage(), e);
        }
    }
}
