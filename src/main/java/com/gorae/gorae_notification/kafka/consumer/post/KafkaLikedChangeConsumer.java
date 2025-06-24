package com.gorae.gorae_notification.kafka.consumer.post;

import com.gorae.gorae_notification.kafka.consumer.post.dto.LikedChangeEvent;
import com.gorae.gorae_notification.kafka.consumer.post.dto.LikedEvent;
import com.gorae.gorae_notification.kafka.consumer.post.service.LikedChangeService;
import com.gorae.gorae_notification.kafka.consumer.post.service.LikedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaLikedChangeConsumer {

    private final LikedChangeService likedChangeService;

    @KafkaListener(
            topics = LikedChangeEvent.Topic,
            groupId = "liked-change",
            containerFactory = "likedChangeKafkaListenerFactory"
    )
    public void handleLikedChangeEvent(LikedChangeEvent event, Acknowledgment ack) {
        try {
            log.info("userId={}님이 좋아요를 취소했습니다.", event.getCommentLikeUserId());
            likedChangeService.processLikedChangeEvent(event);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("좋아요중 오류가 발생헀습니다. {}", e.getMessage(), e);
        }
    }
}
