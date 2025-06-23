package com.gorae.gorae_notification.kafka.consumer.post;

import com.gorae.gorae_notification.kafka.consumer.post.dto.CommentEvent;
import com.gorae.gorae_notification.kafka.consumer.post.dto.LikedEvent;
import com.gorae.gorae_notification.kafka.consumer.post.service.LikedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaLikedConsumer {

    private final LikedService likedService;

    @KafkaListener(
            topics = CommentEvent.Topic,
            groupId = "liked",
            containerFactory = "likedKafkaListenerFactory"
    )
    public void handleLikedEvent(LikedEvent event, Acknowledgment ack) {
        try {
            log.info("userId={}님이 좋아요를 남겼습니다.", event.getCommentLikeUserId());
            likedService.processLikedCommentEvent(event);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("답글 작성중 오류가 발생헀습니다. {}", e.getMessage(), e);
        }
    }
}
